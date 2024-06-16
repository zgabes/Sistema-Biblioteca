import entidades.Emprestimo;
import entidades.Livro;
import entidades.Multa;
import entidades.Usuario;

import java.util.List;

public class Main {
    private static void cadastrarUsuarios(Servicos servicos) {
        servicos.cadastrarUsuario("GABRIEL");
        servicos.cadastrarUsuario("BARBARA");
        servicos.cadastrarUsuario("JOAO BARRETO");
        servicos.cadastrarUsuario("JOAO VICTOR");
        servicos.cadastrarUsuario("STEFANY");
    }

    private static void cadastrarLivros(Servicos servicos) {
        servicos.cadastrarLivro("LIVRO A");
        servicos.cadastrarLivro("LIVRO B");
        servicos.cadastrarLivro("LIVRO C");
        servicos.cadastrarLivro("LIVRO D");
        servicos.cadastrarLivro("LIVRO E");
    }

    private static void printaUsuarios(List<Usuario> usuarios) {
        System.out.println(usuarios.stream().map(Usuario::toString).toList());
    }

    private static void printaLivros(List<Livro> livros) {
        System.out.println(livros.stream().map(Livro::toString).toList());
    }

    private static void printaEmprestimos(List<Emprestimo> emprestimos) {
        System.out.println(emprestimos.stream().map(Emprestimo::toString).toList());
    }

    private static void printaMultas(List<Multa> multas) {
        System.out.println(multas.stream().map(Multa::toString).toList());
    }

    public static void main(String[] args) {
        BancoDeDados bancoDeDados = new BancoDeDados();

        Consultas consultas = new Consultas(bancoDeDados);
        Servicos servicos = new Servicos(bancoDeDados, consultas);

        List<Usuario> usuarios = bancoDeDados.consultaUsuarios();
        List<Livro> livros = bancoDeDados.consultaLivros();
        List<Emprestimo> emprestimos = bancoDeDados.consultaEmprestimos();
        List<Multa> multas = bancoDeDados.consultaMultas();

        try {
            cadastrarUsuarios(servicos);
            cadastrarLivros(servicos);

            servicos.cadastrarEmprestimo(1, 1);
            servicos.cadastrarEmprestimo(2, 2);
            servicos.cadastrarEmprestimo(3, 3);

            servicos.cadastrarMulta(1);
            servicos.cadastrarMulta(2);

            servicos.concluiEmprestimo(3);

            servicos.cadastrarEmprestimo(3, 4);

            servicos.cadastrarMulta(4);
            servicos.cadastrarMulta(4);


        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        printaUsuarios(usuarios);
        printaLivros(livros);
        printaEmprestimos(emprestimos);
        printaMultas(multas);

    }
}