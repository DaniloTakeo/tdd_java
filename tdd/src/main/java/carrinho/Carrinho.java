package carrinho;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	private List<ItemCarrinho> itens = new ArrayList<>();
	private double descontoPercentual = 0.0;
	private double descontoFixo = 0.0;
	private ServicoEstoque servicoEstoque;
	
	public Carrinho() {}
	
	public Carrinho(ServicoEstoque servicoEstoque) {
        this.servicoEstoque = servicoEstoque;
    }

	public void adicionar(Produto produto) {
		if (!servicoEstoque.verificarDisponibilidade(produto, 1)) {
            throw new IllegalStateException("Produto sem estoque");
        }
		ItemCarrinho itemExistente = buscarItem(produto);
		if (itemExistente != null) {
			itemExistente.incrementar();
		} else {
			itens.add(new ItemCarrinho(produto));
		}
	}

	public int getQuantidade(Produto produto) {
		ItemCarrinho item = buscarItem(produto);
		return item != null ? item.getQuantidade() : 0;
	}

	public double getSubtotal(Produto produto) {
		ItemCarrinho item = buscarItem(produto);
		return item != null ? item.getSubtotal() : 0.0;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	private ItemCarrinho buscarItem(Produto produto) {
		return itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst().orElse(null);
	}

	public double getValorTotal() {
		return itens.stream().mapToDouble(ItemCarrinho::getSubtotal).sum();
	}

	public List<Produto> getProdutos() {
		return itens.stream().map(ItemCarrinho::getProduto).toList();
	}

	public void remover(Produto produto) {
		ItemCarrinho item = buscarItem(produto);
		if (item == null)
			return;

		if (item.getQuantidade() > 1) {
			item.decrementar();
		} else {
			itens.remove(item);
		}
	}

	public void aplicarDescontoPercentual(double percentual) {
	    this.descontoPercentual = percentual;
	    this.descontoFixo = 0.0;
	}
	
	public void aplicarDescontoFixo(double valor) {
	    this.descontoFixo = valor;
	    this.descontoPercentual = 0.0;
	}


	public double getValorFinal() {
	    double total = getValorTotal();

	    if (descontoPercentual > 0.0) {
	        total -= total * descontoPercentual / 100.0;
	    } else if (descontoFixo > 0.0) {
	        total -= descontoFixo;
	    }

	    return Math.max(total, 0.0); // impede valor negativo
	}

}