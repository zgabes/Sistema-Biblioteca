import entidades.Emprestimo;
import entidades.Livro;
import entidades.Multa;
import entidades.Usuario;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Consultas {
    private final BancoDeDados bancoDeDados;

    public Consultas(BancoDeDados bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    public List<Emprestimo> consultaEmprestimosAtivos() {
        return this.bancoDeDados.consultaEmprestimos().stream()
                .filter(emprestimo -> !emprestimo.getDevolvido())
                .toList();
    }

    public List<Multa> consultaMultasAtivas() {
        return this.bancoDeDados.consultaMultas().stream()
                .filter(multa -> !multa.getPago())
                .toList();
    }

    public List<Emprestimo> consultaEmprestimosAtivasPorUsuario(Usuario usuario) {
        return this.bancoDeDados.consultaEmprestimos().stream()
                .filter(emprestimo -> emprestimo.getUsuario().equals(usuario))
                .toList();
    }

    public List<Multa> consultaMultasAtivasPorUsuario(Usuario usuario) {
        return this.bancoDeDados.consultaMultas().stream()
                .filter(multa -> multa.getEmprestimo().getUsuario().equals(usuario))
                .toList();
    }

    public Optional<Emprestimo> procuraEmprestimosAtivosPorUsuario(Usuario usuario) {
        return this.bancoDeDados.consultaEmprestimos().stream()
                .filter(emprestimo -> !emprestimo.getDevolvido())
                .filter(emprestimo -> emprestimo.getUsuario().equals(usuario))
                .min(Comparator.comparing(Emprestimo::getDataEmprestimo));
    }

    public Optional<Multa> procuraMultasAtivasPorEmprestimo(Emprestimo emprestimo) {
        return this.bancoDeDados.consultaMultas().stream()
                .filter(multa -> !multa.getPago())
                .filter(multa -> multa.getEmprestimo().equals(emprestimo))
                .min(Comparator.comparing(Multa::getDataAplicada));
    }

    public Usuario procuraUsuarioPorId(Integer id) throws Exception {
        return this.bancoDeDados.consultaUsuarios().stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Usuário com o ID " + id + " não encontrado."));
    }

    public Livro procuraLivroPorId(Integer id) throws Exception {
        return this.bancoDeDados.consultaLivros().stream()
                .filter(livro -> livro.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Livro com o ID " + id + " não encontrado."));
    }

    public Emprestimo procuraEmprestimoPorId(Integer id) throws Exception {
        return this.bancoDeDados.consultaEmprestimos().stream()
                .filter(emprestimo -> emprestimo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Empréstimo com o ID " + id + " não encontrado."));
    }

    public Multa procuraMultaPorId(Integer id) throws Exception {
        return this.bancoDeDados.consultaMultas().stream()
                .filter(multa -> multa.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Multa com o ID " + id + " não encontrado."));
    }
}
