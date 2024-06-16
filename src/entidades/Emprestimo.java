package entidades;

import java.time.LocalDateTime;

public class Emprestimo {
    private Integer id;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private Boolean devolvido;
    private Usuario usuario;
    private Livro livro;

    public Emprestimo(Integer id, Usuario usuario, Livro livro) {
        this.id = id;
        this.dataEmprestimo = LocalDateTime.now();
        this.dataDevolucao = dataEmprestimo.plusWeeks(2);
        this.devolvido = false;
        this.usuario = usuario;
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", devolvido=" + devolvido +
                ", usuario=" + usuario.getId() +
                ", livro=" + livro.getId() +
                '}';
    }

    public Livro getLivro() {
        return livro;
    }

    public Integer getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Boolean getDevolvido() {
        return devolvido;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void marcarComoDevolvido() {
        this.devolvido = true;
        this.livro.incrementaCopiasDisponiveis();
    }
}
