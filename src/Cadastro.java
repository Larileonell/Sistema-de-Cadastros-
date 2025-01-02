import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cadastro {
    private static final String DIRETORIO_USUARIOS = "cadastros/";
    private static final String FORMULARIO_ARQUIVO = "formulario.txt";

    public void cadastrarUsuario(Usuario usuario) {
        try {
            // Cria o diretório de cadastros se não existir
            File diretorio = new File(DIRETORIO_USUARIOS);
            if (!diretorio.exists()) {
                diretorio.mkdir();  // Cria o diretório se ele não existir
            }

            // Define o nome do arquivo com base no nome do usuário
            String nomeArquivo = DIRETORIO_USUARIOS + usuario.getNome().toUpperCase().replace(" ", "") + ".txt";

            // Chama o método salvarRespostasEmArquivo para salvar as informações no arquivo
            salvarRespostasEmArquivo(nomeArquivo, usuario);  // Passando o arquivo e o objeto Usuario como parâmetro

            System.out.println("Cadastro realizado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar o usuário: " + e.getMessage());
        }
    }

    public void salvarRespostasEmArquivo(String nomeArquivo, Usuario usuario) throws IOException {
        // Abre o arquivo para escrita
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("Nome: " + usuario.getNome());
            writer.newLine();  // Adiciona uma nova linha

            writer.write("Email: " + usuario.getEmail());
            writer.newLine();

            writer.write("Idade: " + usuario.getIdade());
            writer.newLine();

            writer.write("Altura: " + usuario.getAltura());
            writer.newLine();

            System.out.println("Informações salvas no arquivo: " + nomeArquivo);
        }
    }

    public void listarUsuarios() {
        File diretorio = new File(DIRETORIO_USUARIOS);
        File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".txt")); // Filtra arquivos .txt

        if (arquivos != null && arquivos.length > 0) {
            System.out.println("Usuários cadastrados:");
            for (File arquivo : arquivos) {

                String nomeUsuario = arquivo.getName().replace(".txt", "");
                System.out.println(nomeUsuario);
            }
        } else {
            System.out.println("Nenhum usuário cadastrado.");
        }
    }

    public List<String> lerFormulario() {
        List<String> perguntas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FORMULARIO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                perguntas.add(linha); // Adiciona cada linha do arquivo à lista
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo do formulário: " + e.getMessage());
        }
        return perguntas;
    }

    public void cadastrarNovaPergunta(String novaPergunta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FORMULARIO_ARQUIVO))) {
            writer.write(novaPergunta);
            writer.newLine();
            System.out.println("Nova Pergunta Cadastrada Com Sucesso");

        } catch (IOException e) {
            System.out.println("Erro ao Adicionar Pergunta" + e.getMessage());
        }
    }

    private List<String> lerArquivo(String caminhoArquivo) {
        List<String> perguntas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                perguntas.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return perguntas;
    }

    public void deletarPergunta(int indice) {
        List<String> perguntas = lerArquivo(FORMULARIO_ARQUIVO);
        if (perguntas.isEmpty()) {
            System.out.println("Nenhuma pergunta disponível para excluir.");
            return;
        }

        if (indice >= 0 && indice < perguntas.size()) {
            perguntas.remove(indice);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FORMULARIO_ARQUIVO))) {
                for (String pergunta : perguntas) {
                    writer.write(pergunta);
                    writer.newLine();
                }
                System.out.println("Pergunta excluída com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao excluir pergunta: " + e.getMessage());
            }
        } else {
            System.out.println("Número inválido.");
        }
    }
    public void pesquisarUsuario(String pesquisa) {
        File diretorio = new File(DIRETORIO_USUARIOS);
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".TXT"));
            if (arquivos != null && arquivos.length > 0) {
                System.out.println("Resultados encontrados:");
                for (File arquivo : arquivos) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            if (linha.contains(pesquisa)) {
                                System.out.println(arquivo.getName() + ": " + linha);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("Nenhum usuário cadastrado.");
            }
        } else {
            System.out.println("Diretório de cadastros não encontrado.");
        }
    }


}
