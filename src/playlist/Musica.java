package playlist;

/**
 * Representa uma música armazenada na playlist.
 */
public class Musica {
    private final String titulo;
    private final String artista;
    private final String album;
    private final int duracao;

    // Cria uma música com título, artista, álbum e duração.
    public Musica(String titulo, String artista, String album, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.duracao = duracao;
    }

    // Retorna o título.
    public String getTitulo() {
        return titulo;
    }

    // Retorna o artista.
    public String getArtista() {
        return artista;
    }

    // Retorna o álbum.
    public String getAlbum() {
        return album;
    }

    // Retorna a duração em segundos.
    public int getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        int minutos = duracao / 60;
        int segundos = duracao % 60;
        return titulo + " - " + artista + " (" + album + ") " + String.format("%d:%02d", minutos, segundos);
    }
}
