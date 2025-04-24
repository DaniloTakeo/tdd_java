package carrinho;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarrinhoTest {
	
	@InjectMocks
    Carrinho carrinho;

    Produto produto;

    @Mock
    private ServicoEstoque servicoEstoque;
	
	@BeforeEach
    void setUp() {
        when(servicoEstoque.verificarDisponibilidade(any(), anyInt())).thenReturn(true);
        carrinho = new Carrinho(servicoEstoque);
    }

	@Test
    void deveAdicionarProdutoAoCarrinho() {
        Produto produto = new Produto("Camiseta", 59.90);
        carrinho.adicionar(produto);

        assertThat(carrinho.getProdutos()).contains(produto);
    }

    @Test
    void deveCalcularValorTotalDoCarrinho() {
        carrinho.adicionar(new Produto("Camisa", 50.0));
        carrinho.adicionar(new Produto("Calça", 120.0));

        double total = carrinho.getValorTotal();

        assertThat(total).isEqualTo(170.0);
    }

    @Test
    void deveRemoverUmaUnidadeDoProduto() {
        Produto produto = new Produto("Camiseta", 59.90);

        carrinho.adicionar(produto);
        carrinho.adicionar(produto);

        carrinho.remover(produto);

        assertThat(carrinho.getQuantidade(produto)).isEqualTo(1);
        assertThat(carrinho.getSubtotal(produto)).isEqualTo(59.90);
    }

    @Test
    void deveRemoverProdutoDoCarrinhoQuandoQuantidadeChegarAZero() {
        Produto produto = new Produto("Calça", 120.00);

        carrinho.adicionar(produto);
        carrinho.remover(produto);

        assertThat(carrinho.getQuantidade(produto)).isEqualTo(0);
        assertThat(carrinho.getProdutos()).doesNotContain(produto);
    }

    @Test
    void deveAcumularQuantidadeAoAdicionarMesmoProduto() {
        Produto produto = new Produto("Mouse", 80.0);

        carrinho.adicionar(produto);
        carrinho.adicionar(produto);

        assertThat(carrinho.getQuantidade(produto)).isEqualTo(2);
        assertThat(carrinho.getSubtotal(produto)).isEqualTo(160.0);
    }

    @Test
    void deveAplicarDescontoPercentualAoValorTotal() {
        Produto produto = new Produto("Fone de ouvido", 100.0);
        carrinho.adicionar(produto);
        carrinho.adicionar(produto);

        carrinho.aplicarDescontoPercentual(10);

        assertThat(carrinho.getValorFinal()).isEqualTo(180.0);
    }

    @Test
    void deveAplicarDescontoFixoAoValorTotal() {
        Produto produto = new Produto("Monitor", 500.0);
        carrinho.adicionar(produto);
        carrinho.adicionar(produto);

        carrinho.aplicarDescontoFixo(150.0);

        assertThat(carrinho.getValorFinal()).isEqualTo(850.0);
    }

    @Test
    void valorFinalNaoPodeSerNegativoComDescontoFixoMuitoAlto() {
        Produto produto = new Produto("Teclado", 120.0);
        carrinho.adicionar(produto);

        carrinho.aplicarDescontoFixo(200.0);

        assertThat(carrinho.getValorFinal()).isEqualTo(0.0);
    }
    
    @Test
    void deveImpedirAdicionarProdutoSemEstoque() {
        when(servicoEstoque.verificarDisponibilidade(produto, 1)).thenReturn(false);

        assertThatThrownBy(() -> carrinho.adicionar(produto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Produto sem estoque");
    }

    @Test
    void deveAdicionarProdutoComEstoqueDisponivel() {
        when(servicoEstoque.verificarDisponibilidade(produto, 1)).thenReturn(true);

        carrinho.adicionar(produto);

        assertThat(carrinho.getProdutos()).contains(produto);
    }

}
