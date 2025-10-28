package pedidos;

/**
 * Representa um pedido realizado na cafeteria.
 */
public class Pedido {
    private final int id;
    private final String descricao;

    // Registra um pedido com identificador e descrição.
    public Pedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Devolve o código do pedido.
    public int getId() {
        return id;
    }

    // Devolve o texto que descreve o pedido.
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Pedido " + id + ": " + descricao;
    }
}
