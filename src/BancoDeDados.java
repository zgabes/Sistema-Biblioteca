import entidades.Emprestimo;
import entidades.Livro;
import entidades.Multa;
import entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    private final List<Usuario> usuarios;
    private final List<Livro> livros;
    private final List<Emprestimo> emprestimos;
    private final List<Multa> multas;

    public BancoDeDados() {
        this.usuarios = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.multas = new ArrayList<>();
    }

    // INICIO LISTAGENS
    public List<Usuario> consultaUsuarios() {
        return this.usuarios;
    }

    public List<Livro> consultaLivros() {
        return this.livros;
    }

    public List<Emprestimo> consultaEmprestimos() {
        return this.emprestimos;
    }

    public List<Multa> consultaMultas() {
        return this.multas;
    }
    // FIM LISTAGENS

    // INICIO OPERACOES BASICAS
    public void adicionaUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void adicionaLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void adicionaEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public void adicionaMulta(Multa multa) {
        this.multas.add(multa);
    }
    // FIM OPERACOES BASICAS

    // INICIO GERADOR DE IDS
    public Integer geraIdUsuario() {
        return this.usuarios.stream()
                .map(Usuario::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }

    public Integer geraIdLivro() {
        return this.livros.stream()
                .map(Livro::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }

    public Integer geraIdEmprestimo() {
        return this.emprestimos.stream()
                .map(Emprestimo::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }

    public Integer geraIdMulta() {
        return this.multas.stream()
                .map(Multa::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }
    // FIM GERADOR DE IDS
}
