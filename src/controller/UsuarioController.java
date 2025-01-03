package controller;
import dominio.entidade.usuario.Usuario;
import dominio.repositorio.Cadastro;
import dominio.exceptions.*;

import java.util.List;
import java.util.Scanner;
public class UsuarioController {
    private Cadastro cadastro;
    private Scanner scanner;

    public UsuarioController() {
        this.cadastro = new Cadastro();
        this.scanner = new Scanner(System.in);
    }

    public static void cadastrarUsuario(Scanner scanner, Cadastro cadastro) {
        try {
            String nome = obterResposta(scanner, "Qual seu nome completo?");
            String email = obterResposta(scanner, "Qual seu email de contato?");
            int idade = Integer.parseInt(obterResposta(scanner, "Qual sua idade?"));
            String altura = obterResposta(scanner, "Qual sua altura? (Formato: 1,75)");

            Usuario usuario = new Usuario(nome, email, idade, altura);
            cadastro.cadastrarUsuario(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (NameFormatException | EmailFormatException | EmailExistException |
                 AgeException | HeightFormatException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Formato inválido para idade ou altura. Por favor, revise os dados.");
        }
    }

    public static void cadastrarNovaPergunta(Scanner scanner, Cadastro cadastro) {
        String novaPergunta = obterResposta(scanner, "Digite a nova pergunta para o formulário:");
        cadastro.cadastrarNovaPergunta(novaPergunta);
        System.out.println("Perguntas no formulário agora:");
        List<String> perguntas = cadastro.lerFormulario();
        perguntas.forEach(System.out::println);
    }

    public static void deletarPergunta(Scanner scanner, Cadastro cadastro) {
        int indice = Integer.parseInt(obterResposta(scanner, "Digite o número da pergunta que deseja deletar:")) - 1;
        cadastro.deletarPergunta(indice);
    }

    public static void pesquisarUsuario(Scanner scanner, Cadastro cadastro) {
        String termo = obterResposta(scanner, "Digite o nome ou parte dele, para buscar usuário");
        cadastro.buscarUsuarios(termo);
    }

    public static String obterResposta(Scanner scanner, String pergunta) {
        System.out.println(pergunta);
        return scanner.nextLine();
    }
}
