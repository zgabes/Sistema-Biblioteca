package entidades;

public class Livro {
    private Integer id;
    private String titulo;
    private String autor;
    private String genero;
    private Integer copiasDisponiveis;

    public Livro(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.copiasDisponiveis = 1;
    }

    public Livro(Integer id, String titulo, String autor, String genero, Integer copiasDisponiveis) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.copiasDisponiveis = copiasDisponiveis;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", copiasDisponiveis=" + copiasDisponiveis +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Boolean possuiCopiasDisponiveis() {
        return copiasDisponiveis > 0;
    }

    public void incrementaCopiasDisponiveis() {
        copiasDisponiveis ++;
    }

    public void decrementaCopiasDisponiveis() {
        copiasDisponiveis --;
    }
}
