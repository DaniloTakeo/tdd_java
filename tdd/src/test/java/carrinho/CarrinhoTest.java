package carrinho;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarrinhoTest {
	@Mock
	private ServicoEstoque servicoEstoque;

	@InjectMocks
	private Carrinho carrinho;

	@Captor
	private ArgumentCaptor<Produto> produtoCaptor;
	
	@Nested
	class QuandoProdutoEstaDisponivel {

	    @Test
	    void deveAdicionarProdutoAoCarrinho() {
	        // arrange
	        Produto produto = new Produto("Camiseta", 59.90);
	        when(servicoEstoque.verificarDisponibilidade(produto, 1)).thenReturn(true);

	        // act
	        carrinho.adicionar(produto);

	        // assert
	        assertThat(carrinho.getProdutos()).contains(produto);
	    }
	}
	
	@Nested
	class QuandoProdutoNaoEstaDisponivel {

	    @Test
	    void deveLancarExcecaoAoAdicionarProdutoSemEstoque() {
	        // arrange
	        Produto produto = new Produto("Camiseta", 59.90);
	        when(servicoEstoque.verificarDisponibilidade(produto, 1)).thenReturn(false);

	        // act + assert
	        assertThatThrownBy(() -> carrinho.adicionar(produto))
	            .isInstanceOf(IllegalStateException.class)
	            .hasMessage("Produto sem estoque");
	    }
	}
	
	@Nested
	class ValidacoesDeEstoque {

	    @Test
	    void deveVerificarEstoqueAoAdicionarProduto() {
	        // arrange
	        Produto produto = new Produto("Notebook", 3000.0);
	        when(servicoEstoque.verificarDisponibilidade(any(), eq(1))).thenReturn(true);

	        // act
	        carrinho.adicionar(produto);

	        // assert
	        verify(servicoEstoque).verificarDisponibilidade(produtoCaptor.capture(), eq(1));
	        assertThat(produtoCaptor.getValue().getNome()).isEqualTo("Notebook");
	    }
	}
}