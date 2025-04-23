package carrinho;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CarrinhoTest {

    @Test
    void deveAdicionarProdutoAoCarrinho() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Camiseta", 59.90);

        carrinho.adicionar(produto);

        assertThat(carrinho.getProdutos()).contains(produto);
    }
    
    @Test
    void deveCalcularValorTotalDoCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Camisa", 50.0));
        carrinho.adicionar(new Produto("Calça", 120.0));

        double total = carrinho.getValorTotal();

        assertThat(total).isEqualTo(170.0);
    }
    
    @Test
    void deveRemoverUmaUnidadeDoProduto() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Camiseta", 59.90);

        carrinho.adicionar(produto);
        carrinho.adicionar(produto); // Agora temos 2 unidades

        carrinho.remover(produto); // Remove 1 unidade

        assertThat(carrinho.getQuantidade(produto)).isEqualTo(1);
        assertThat(carrinho.getSubtotal(produto)).isEqualTo(59.90);
    }
    
    @Test
    void deveRemoverProdutoDoCarrinhoQuandoQuantidadeChegarAZero() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Calça", 120.00);

        carrinho.adicionar(produto); // 1 unidade

        carrinho.remover(produto); // Remove 1 => quantidade 0, remove item

        assertThat(carrinho.getQuantidade(produto)).isEqualTo(0);
        assertThat(carrinho.getProdutos()).doesNotContain(produto);
    }
    
    @Test
    void deveAcumularQuantidadeAoAdicionarMesmoProduto() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Mouse", 80.0);

        carrinho.adicionar(produto);
        carrinho.adicionar(produto);

        assertThat(carrinho.getQuantidade(produto)).isEqualTo(2);
        assertThat(carrinho.getSubtotal(produto)).isEqualTo(160.0);
    }
    
    @Test
    void deveAplicarDescontoPercentualAoValorTotal() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Fone de ouvido", 100.0);
        carrinho.adicionar(produto);
        carrinho.adicionar(produto); // Total bruto = 200

        carrinho.aplicarDescontoPercentual(10); // 10% de desconto

        assertThat(carrinho.getValorFinal()).isEqualTo(180.0);
    }
    
    @Test
    void deveAplicarDescontoFixoAoValorTotal() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Monitor", 500.0);
        carrinho.adicionar(produto);
        carrinho.adicionar(produto); // total = 1000

        carrinho.aplicarDescontoFixo(150.0);

        assertThat(carrinho.getValorFinal()).isEqualTo(850.0);
    }
    
    @Test
    void valorFinalNaoPodeSerNegativoComDescontoFixoMuitoAlto() {
        Carrinho carrinho = new Carrinho();
        Produto produto = new Produto("Teclado", 120.0);
        carrinho.adicionar(produto); // total = 120

        carrinho.aplicarDescontoFixo(200.0);

        assertThat(carrinho.getValorFinal()).isEqualTo(0.0);
    }
}
