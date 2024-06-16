import entidades.*;

import java.util.Optional;

public class Servicos {
    private final BancoDeDados bancoDeDados;
    private final Consultas consultas;

    public Servicos(BancoDeDados bancoDeDados, Consultas consultas) {
        this.bancoDeDados = bancoDeDados;
        this.consultas = consultas;
    }

    public Usuario cadastrarUsuario(String nome, String email, String telefone) {
        Integer id = this.bancoDeDados.geraIdUsuario();

        Usuario usuario = new Usuario(id, nome, email, telefone);

        this.bancoDeDados.adicionaUsuario(usuario);

        return usuario;
    }

    public Usuario cadastrarUsuario(String nome) {
        Integer id = this.bancoDeDados.geraIdUsuario();

        Usuario usuario = new Usuario(id, nome);

        this.bancoDeDados.adicionaUsuario(usuario);

        return usuario;
    }

    public Livro cadastrarLivro(String titulo, String autor, String genero, Integer copiasDisponiveis) {
        Integer id = this.bancoDeDados.geraIdLivro();

        Livro livro = new Livro(id, titulo, autor, genero, copiasDisponiveis);

        this.bancoDeDados.adicionaLivro(livro);

        return livro;
    }

    public Livro cadastrarLivro(String titulo) {
        Integer id = this.bancoDeDados.geraIdLivro();

        Livro livro = new Livro(id, titulo);

        this.bancoDeDados.adicionaLivro(livro);

        return livro;
    }

    public Emprestimo cadastrarEmprestimo(Integer usuarioId, Integer livroId) throws Exception {
        Usuario usuario = this.consultas.procuraUsuarioPorId(usuarioId);
        Livro livro = this.consultas.procuraLivroPorId(livroId);

        Optional<Emprestimo> possivelEmprestimo = this.consultas.procuraEmprestimosAtivosPorUsuario(usuario);

        if (possivelEmprestimo.isPresent()) {
            Optional<Multa> possivelMulta = this.consultas.procuraMultasAtivasPorEmprestimo(possivelEmprestimo.get());
            if (possivelMulta.isPresent()) {
                throw new Exception("Usuário possuí multa ativa - ID MULTA: " + possivelMulta.get().getId());
            }

            throw new Exception("Usuário possuí empréstimo ativo - ID EMPRÉSTIMO: " + possivelEmprestimo.get().getId());
        }

        if (!livro.possuiCopiasDisponiveis()) {
            throw new Exception("Livro não possuí mais copias disponíveis - ID LIVRO: " + livro.getId());
        }

        Integer id = this.bancoDeDados.geraIdEmprestimo();

        Emprestimo emprestimo = new Emprestimo(id, usuario, livro);

        livro.decrementaCopiasDisponiveis();

        this.bancoDeDados.adicionaEmprestimo(emprestimo);

        return emprestimo;
    }

    public Multa cadastrarMulta(Integer emprestimoId) throws Exception {
        Emprestimo emprestimo = this.consultas.procuraEmprestimoPorId(emprestimoId);

        Optional<Multa> possivelMulta = this.consultas.procuraMultasAtivasPorEmprestimo(emprestimo);

        if(possivelMulta.isPresent()) {
            throw new Exception("Já existe multa cadastrada nesse empréstimo - ID MULTA: " + possivelMulta.get().getId());
        }

        Integer id = this.bancoDeDados.geraIdMulta();

        Multa multa = new Multa(id, emprestimo);

        this.bancoDeDados.adicionaMulta(multa);

        return multa;
    }

    public void concluiEmprestimo(Integer emprestimoId) throws Exception {
        Emprestimo emprestimo = this.consultas.procuraEmprestimoPorId(emprestimoId);

        if (emprestimo.getDevolvido()) {
            throw new Exception("A empréstimo com o ID " + emprestimo.getId() + " já foi devolvido.");
        }

        emprestimo.marcarComoDevolvido();
    }

    public void concluiMulta(Integer multaId) throws Exception {
        Multa multa = this.consultas.procuraMultaPorId(multaId);

        if (multa.getPago()) {
            throw new Exception("A multa com o ID " + multa.getId() + " já foi paga.");
        }

        multa.pagaMulta();
    }
}
