package pedidos;

/**
 * Implementa uma fila de pedidos utilizando lista simplesmente ligada.
 */
public class FilaPedidos {
    private static class NoPedido {
        Pedido valor;
        NoPedido proximo;

        NoPedido(Pedido valor) {
            this.valor = valor;
        }
    }

    private NoPedido inicio;
    private NoPedido fim;
    private int tamanho;

    // Indica se não existem pedidos aguardando.
    public boolean estaVazia() {
        return tamanho == 0;
    }

    // Coloca um novo pedido no fim da fila.
    public void enfileirar(Pedido pedido) {
        NoPedido novo = new NoPedido(pedido);
        if (fim == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }
        tamanho++;
    }

    // Remove e devolve o pedido mais antigo.
    public Pedido desenfileirar() {
        if (inicio == null) {
            return null;
        }
        NoPedido removido = inicio;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        removido.proximo = null;
        tamanho--;
        return removido.valor;
    }

    // Exibe todos os pedidos pendentes no console.
    public void imprimir() {
        if (inicio == null) {
            System.out.println("Nenhum pedido pendente.");
            return;
        }
        System.out.println("Pedidos pendentes (total: " + tamanho + "):");
        NoPedido atual = inicio;
        while (atual != null) {
            System.out.println("- ID: " + atual.valor.getId() + ", Descrição: " + atual.valor.getDescricao());
            atual = atual.proximo;
        }
    }

    // Informa a quantidade de pedidos atualmente na fila.
    public int getTamanho() {
        return tamanho;
    }
}
