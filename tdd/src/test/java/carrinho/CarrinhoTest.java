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
    void deveRemoverProdutoDoCarrinho() {
        Carrinho carrinho = new Carrinho();
        Produto produto1 = new Produto("Camiseta", 59.90);
        Produto produto2 = new Produto("Calça", 120.00);

        carrinho.adicionar(produto1);
        carrinho.adicionar(produto2);

        carrinho.remover(produto1);

        assertThat(carrinho.getProdutos()).doesNotContain(produto1);
        assertThat(carrinho.getProdutos()).contains(produto2);
    }
}
