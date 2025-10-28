package pedidos;

/**
 * Implementa uma pilha de pedidos utilizando lista simplesmente ligada.
 */
public class PilhaPedidos {
    private static class NoPedido {
        Pedido valor;
        NoPedido proximo;

        NoPedido(Pedido valor) {
            this.valor = valor;
        }
    }

    private NoPedido topo;
    private int tamanho;

    // Indica se não existem pedidos cancelados na pilha.
    public boolean estaVazia() {
        return tamanho == 0;
    }

    // Coloca um pedido cancelado no topo.
    public void empilhar(Pedido pedido) {
        NoPedido novo = new NoPedido(pedido);
        novo.proximo = topo;
        topo = novo;
        tamanho++;
    }

    // Remove e devolve o pedido mais recente.
    public Pedido desempilhar() {
        if (topo == null) {
            return null;
        }
        NoPedido removido = topo;
        topo = topo.proximo;
        removido.proximo = null;
        tamanho--;
        return removido.valor;
    }

    // Exibe todos os pedidos cancelados no console.
    public void imprimir() {
        if (topo == null) {
            System.out.println("Nenhum pedido cancelado.");
            return;
        }
        System.out.println("Pedidos cancelados (total: " + tamanho + "):");
        NoPedido atual = topo;
        while (atual != null) {
            System.out.println("- ID: " + atual.valor.getId() + ", Descrição: " + atual.valor.getDescricao());
            atual = atual.proximo;
        }
    }

    // Informa a quantidade de pedidos armazenados na pilha.
    public int getTamanho() {
        return tamanho;
    }
}
