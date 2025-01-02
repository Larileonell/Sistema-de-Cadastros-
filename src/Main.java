import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Cadastro cadastro = new Cadastro();

            while (true) {
                exibirMenu();

                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarUsuario(scanner, cadastro);
                        break;
                    case 2:
                        cadastro.listarUsuarios();
                        break;
                    case 3:
                        cadastrarNovaPergunta(scanner, cadastro);
                        break;
                    case 4:
                        deletarPergunta(scanner, cadastro);
                        break;
                    case 5:
                        pesquisarUsuario(scanner, cadastro);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }

        public static void exibirMenu() {
            System.out.println("Menu:");
            System.out.println("1 - Cadastrar o usuário");
            System.out.println("2 - Listar todos usuários cadastrados");
            System.out.println("3 - Cadastrar nova pergunta no formulário");
            System.out.println("4 - Deletar pergunta do formulário");
            System.out.println("5 - Pesquisar usuário por nome ou idade ou email");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
        }

    public static void cadastrarUsuario(Scanner scanner, Cadastro cadastro) {
        String nome = obterResposta(scanner, "Qual seu nome completo?");
        String email = obterResposta(scanner, "Qual seu email de contato?");
        int idade = Integer.parseInt(obterResposta(scanner, "Qual sua idade?"));
        double altura = Double.parseDouble(obterResposta(scanner, "Qual sua altura?"));


        Usuario usuario = new Usuario(nome, email, idade, altura);
        cadastro.cadastrarUsuario(usuario);
    }

        public static void cadastrarNovaPergunta(Scanner scanner, Cadastro cadastro) {
            String novaPergunta = obterResposta(scanner, "Digite a nova pergunta para o formulário:");
            cadastro.cadastrarNovaPergunta(novaPergunta);
        }

        public static void deletarPergunta(Scanner scanner, Cadastro cadastro) {
            int indice = Integer.parseInt(obterResposta(scanner, "Digite o número da pergunta que deseja deletar:")) - 1;
            cadastro.deletarPergunta(indice);
        }

        public static void pesquisarUsuario(Scanner scanner, Cadastro cadastro) {
            String pesquisa = obterResposta(scanner, "Digite o nome, idade ou e-mail para pesquisa:");
            cadastro.pesquisarUsuario(pesquisa);
        }

        public static String obterResposta(Scanner scanner, String pergunta) {
            System.out.println(pergunta);
            return scanner.nextLine();
        }
    }