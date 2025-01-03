package vision;

import dominio.repositorio.Cadastro;

import java.util.Scanner;

import static controller.UsuarioController.*;

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
            System.out.println("5 - Pesquisar usuário ");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
        }


    }