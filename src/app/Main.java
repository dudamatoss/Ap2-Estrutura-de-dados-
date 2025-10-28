package app;

import java.util.Scanner;
import pedidos.GestorPedidos;
import pedidos.Pedido;
import playlist.Musica;
import playlist.Playlist;

/**
 * Interface de linha de comando que integra o sistema de pedidos e o gerenciador de playlist.
 */
public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    // Inicia o sistema e mantém o menu principal ativo.
    public static void main(String[] args) {
        GestorPedidos gestorPedidos = new GestorPedidos();
        Playlist playlist = new Playlist();

        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerInteiro();
            switch (opcao) {
                case 1 -> menuPedidos(gestorPedidos);
                case 2 -> menuPlaylist(playlist);
                case 3 -> executando = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        System.out.println("Programa encerrado. Obrigado por utilizar o sistema!");
    }

    // Mostra as opções gerais do programa.
    private static void exibirMenuPrincipal() {
        System.out.println("============================");
        System.out.println("1. Gerenciar pedidos da cafeteria");
        System.out.println("2. Gerenciar playlist de músicas");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Controla o fluxo de ações da área de pedidos.
    private static void menuPedidos(GestorPedidos gestorPedidos) {
        boolean voltar = false;
        while (!voltar) {
            exibirMenuPedidos();
            int opcao = lerInteiro();
            switch (opcao) {
                case 1 -> adicionarPedido(gestorPedidos);
                case 2 -> gestorPedidos.atenderPedido();
                case 3 -> gestorPedidos.cancelarPedido();
                case 4 -> gestorPedidos.restaurarPedido();
                case 5 -> gestorPedidos.imprimirPendentes();
                case 6 -> gestorPedidos.imprimirCancelados();
                case 7 -> voltar = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // Mostra as ações disponíveis para pedidos.
    private static void exibirMenuPedidos() {
        System.out.println("--- Menu de Pedidos ---");
        System.out.println("1. Adicionar novo pedido");
        System.out.println("2. Atender pedido");
        System.out.println("3. Cancelar pedido");
        System.out.println("4. Restaurar pedido cancelado");
        System.out.println("5. Imprimir pedidos pendentes");
        System.out.println("6. Imprimir pedidos cancelados");
        System.out.println("7. Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
    }

    // Coleta os dados de um pedido e envia para a fila.
    private static void adicionarPedido(GestorPedidos gestorPedidos) {
        System.out.print("Informe o ID do pedido: ");
        int id = lerInteiro();
        System.out.print("Informe a descrição do pedido: ");
        String descricao = SCANNER.nextLine();
        gestorPedidos.adicionarPedido(new Pedido(id, descricao));
    }

    // Controla o fluxo de ações da playlist.
    private static void menuPlaylist(Playlist playlist) {
        boolean voltar = false;
        while (!voltar) {
            exibirMenuPlaylist();
            int opcao = lerInteiro();
            switch (opcao) {
                case 1 -> playlist.proximaMusica();
                case 2 -> playlist.musicaAnterior();
                case 3 -> playlist.ordenarPorTitulo();
                case 4 -> playlist.ordenarPorArtista();
                case 5 -> playlist.tocarMusicaAtual();
                case 6 -> adicionarMusica(playlist);
                case 7 -> removerMusica(playlist);
                case 8 -> playlist.listarMusicas();
                case 9 -> voltar = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // Mostra as ações disponíveis para a playlist.
    private static void exibirMenuPlaylist() {
        System.out.println("--- Gerenciador de Playlist ---");
        System.out.println("1. Próxima música");
        System.out.println("2. Música anterior");
        System.out.println("3. Ordenar playlist por título");
        System.out.println("4. Ordenar playlist por artista");
        System.out.println("5. Tocar música atual");
        System.out.println("6. Adicionar música");
        System.out.println("7. Remover música pelo título");
        System.out.println("8. Listar músicas");
        System.out.println("9. Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
    }

    // Pede os dados de uma música e escolhe onde inseri-la.
    private static void adicionarMusica(Playlist playlist) {
        System.out.println("Adicionar música");
        System.out.print("Título: ");
        String titulo = SCANNER.nextLine();
        System.out.print("Artista: ");
        String artista = SCANNER.nextLine();
        System.out.print("Álbum: ");
        String album = SCANNER.nextLine();
        System.out.print("Duração em segundos: ");
        int duracao = lerInteiro();
        Musica musica = new Musica(titulo, artista, album, duracao);

        System.out.println("Onde deseja inserir a música?");
        System.out.println("1. Início");
        System.out.println("2. Fim");
        System.out.println("3. Posição específica");
        System.out.print("Escolha uma opção: ");
        int escolha = lerInteiro();
        switch (escolha) {
            case 1 -> playlist.adicionarNoInicio(musica);
            case 2 -> playlist.adicionarNoFim(musica);
            case 3 -> {
                System.out.print("Informe a posição desejada: ");
                int posicao = lerInteiro();
                playlist.adicionarNaPosicao(musica, posicao);
            }
            default -> {
                System.out.println("Opção inválida. A música será adicionada ao fim da playlist.");
                playlist.adicionarNoFim(musica);
            }
        }
        System.out.println("Música adicionada: " + musica);
    }

    // Remove uma música da playlist pelo título informado.
    private static void removerMusica(Playlist playlist) {
        System.out.print("Informe o título da música a ser removida: ");
        String titulo = SCANNER.nextLine();
        Musica removida = playlist.removerPorTitulo(titulo);
        if (removida == null) {
            System.out.println("Música não encontrada na playlist.");
        } else {
            System.out.println("Música removida: " + removida);
        }
    }

    // Lê um número inteiro do teclado, insistindo até receber um valor válido.
    private static int lerInteiro() {
        while (true) {
            String entrada = SCANNER.nextLine();
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }
}
