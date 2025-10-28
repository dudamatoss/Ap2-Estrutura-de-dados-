package playlist;

/**
 * Implementa uma playlist de músicas utilizando lista duplamente ligada.
 */
public class Playlist {
    private static class NoMusica {
        Musica valor;
        NoMusica proximo;
        NoMusica anterior;

        NoMusica(Musica valor) {
            this.valor = valor;
        }
    }

    private NoMusica inicio;
    private NoMusica fim;
    private NoMusica atual;
    private int tamanho;

    // Adiciona uma música no início da lista.
    public void adicionarNoInicio(Musica musica) {
        NoMusica novo = new NoMusica(musica);
        novo.proximo = inicio;
        if (inicio != null) {
            inicio.anterior = novo;
        }
        inicio = novo;
        if (fim == null) {
            fim = novo;
        }
        if (atual == null) {
            atual = novo;
        }
        tamanho++;
    }

    // Adiciona uma música no final da lista.
    public void adicionarNoFim(Musica musica) {
        NoMusica novo = new NoMusica(musica);
        novo.anterior = fim;
        if (fim != null) {
            fim.proximo = novo;
        }
        fim = novo;
        if (inicio == null) {
            inicio = novo;
        }
        if (atual == null) {
            atual = novo;
        }
        tamanho++;
    }

    // Insere uma música na posição indicada (considerando zero como início).
    public void adicionarNaPosicao(Musica musica, int posicao) {
        if (posicao <= 0) {
            adicionarNoInicio(musica);
            return;
        }
        if (posicao >= tamanho) {
            adicionarNoFim(musica);
            return;
        }
        NoMusica atualNo = inicio;
        for (int i = 0; i < posicao; i++) {
            atualNo = atualNo.proximo;
        }
        NoMusica anterior = atualNo.anterior;
        NoMusica novo = new NoMusica(musica);
        novo.proximo = atualNo;
        novo.anterior = anterior;
        anterior.proximo = novo;
        atualNo.anterior = novo;
        tamanho++;
    }

    // Remove uma música buscando pelo título (ignorando maiúsculas e minúsculas).
    public Musica removerPorTitulo(String titulo) {
        NoMusica no = inicio;
        while (no != null) {
            if (no.valor.getTitulo().equalsIgnoreCase(titulo)) {
                Musica musica = no.valor;
                NoMusica anterior = no.anterior;
                NoMusica proximo = no.proximo;
                if (anterior != null) {
                    anterior.proximo = proximo;
                } else {
                    inicio = proximo;
                }
                if (proximo != null) {
                    proximo.anterior = anterior;
                } else {
                    fim = anterior;
                }
                if (atual == no) {
                    atual = proximo != null ? proximo : anterior;
                }
                no.proximo = null;
                no.anterior = null;
                tamanho--;
                if (tamanho == 0) {
                    atual = null;
                }
                return musica;
            }
            no = no.proximo;
        }
        return null;
    }

    // Avança para a próxima música da lista.
    public void proximaMusica() {
        if (atual == null) {
            System.out.println("A playlist está vazia.");
            return;
        }
        if (atual.proximo != null) {
            atual = atual.proximo;
            System.out.println("Avançando para: " + atual.valor);
        } else {
            System.out.println("Você já está na última música da playlist.");
        }
    }

    // Volta para a música anterior da lista.
    public void musicaAnterior() {
        if (atual == null) {
            System.out.println("A playlist está vazia.");
            return;
        }
        if (atual.anterior != null) {
            atual = atual.anterior;
            System.out.println("Voltando para: " + atual.valor);
        } else {
            System.out.println("Você já está na primeira música da playlist.");
        }
    }

    // Exibe os detalhes da música atualmente selecionada.
    public void tocarMusicaAtual() {
        if (atual == null) {
            System.out.println("Nenhuma música selecionada.");
            return;
        }
        Musica musica = atual.valor;
        System.out.println("Reproduzindo agora:");
        System.out.println("Título: " + musica.getTitulo());
        System.out.println("Artista: " + musica.getArtista());
        System.out.println("Álbum: " + musica.getAlbum());
        System.out.println("Duração: " + musica.getDuracao() + " segundos");
    }

    // Lista todas as músicas na ordem atual.
    public void listarMusicas() {
        if (inicio == null) {
            System.out.println("A playlist está vazia.");
            return;
        }
        System.out.println("Playlist atual:");
        NoMusica no = inicio;
        int indice = 0;
        while (no != null) {
            System.out.println(indice + ": " + no.valor);
            no = no.proximo;
            indice++;
        }
    }

    // Ordena a playlist pelo título.
    public void ordenarPorTitulo() {
        ordenar(true);
        System.out.println("Playlist ordenada por título.");
    }

    // Ordena a playlist pelo artista.
    public void ordenarPorArtista() {
        ordenar(false);
        System.out.println("Playlist ordenada por artista.");
    }

    // Realiza uma ordenação por bolha com base no título ou artista.
    private void ordenar(boolean porTitulo) {
        if (tamanho < 2) {
            return;
        }
        boolean trocou;
        do {
            trocou = false;
            NoMusica no = inicio;
            while (no != null && no.proximo != null) {
                Musica atualMusica = no.valor;
                Musica proximaMusica = no.proximo.valor;
                if (comparar(atualMusica, proximaMusica, porTitulo) > 0) {
                    no.valor = proximaMusica;
                    no.proximo.valor = atualMusica;
                    trocou = true;
                }
                no = no.proximo;
            }
        } while (trocou);
    }

    // Compara duas músicas de acordo com o critério escolhido.
    private int comparar(Musica a, Musica b, boolean porTitulo) {
        String primeiro = porTitulo ? a.getTitulo() : a.getArtista();
        String segundo = porTitulo ? b.getTitulo() : b.getArtista();
        return primeiro.compareToIgnoreCase(segundo);
    }
}
