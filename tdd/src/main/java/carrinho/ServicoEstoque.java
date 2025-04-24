package carrinho;

public interface ServicoEstoque {
    boolean verificarDisponibilidade(Produto produto, int quantidade);
}