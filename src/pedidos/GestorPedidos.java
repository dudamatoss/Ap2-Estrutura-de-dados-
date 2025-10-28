package pedidos;

/**
 * Coordena as operações de fila e pilha relacionadas aos pedidos da cafeteria.
 */
public class GestorPedidos {
    private final FilaPedidos filaPendentes = new FilaPedidos();
    private final PilhaPedidos pilhaCancelados = new PilhaPedidos();

    // Insere um novo pedido na fila de pendentes.
    public void adicionarPedido(Pedido pedido) {
        filaPendentes.enfileirar(pedido);
        System.out.println("Pedido adicionado à fila de pendentes: " + pedido);
    }

    // Atende o pedido mais antigo da fila.
    public void atenderPedido() {
        Pedido pedido = filaPendentes.desenfileirar();
        if (pedido == null) {
            System.out.println("Não há pedidos para atender.");
        } else {
            System.out.println("Pedido atendido: " + pedido);
        }
    }

    // Cancela o pedido mais antigo e guarda na pilha.
    public void cancelarPedido() {
        Pedido pedido = filaPendentes.desenfileirar();
        if (pedido == null) {
            System.out.println("Não há pedidos para cancelar.");
        } else {
            pilhaCancelados.empilhar(pedido);
            System.out.println("Pedido cancelado: " + pedido);
        }
    }

    // Restaura o pedido mais recente dos cancelados.
    public void restaurarPedido() {
        Pedido pedido = pilhaCancelados.desempilhar();
        if (pedido == null) {
            System.out.println("Não há pedidos cancelados para restaurar.");
        } else {
            filaPendentes.enfileirar(pedido);
            System.out.println("Pedido restaurado: " + pedido);
        }
    }

    // Mostra todos os pedidos pendentes.
    public void imprimirPendentes() {
        filaPendentes.imprimir();
    }

    // Mostra todos os pedidos cancelados.
    public void imprimirCancelados() {
        pilhaCancelados.imprimir();
    }
}
