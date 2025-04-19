package carrinho;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public double getValorTotal() {
        return produtos.stream()
                       .mapToDouble(Produto::getPreco)
                       .sum();
    }
    
    public void remover(Produto produto) {
        produtos.remove(produto);
    }
}