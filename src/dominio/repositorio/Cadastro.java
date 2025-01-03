package dominio.repositorio;

import dominio.entidade.usuario.Usuario;
import dominio.exceptions.*;

import java.io.*;
import java.util.*;

public class Cadastro {
    private static final String DIRETORIO_USUARIOS = "cadastros/";
    private static final String FORMULARIO_ARQUIVO = "formulario.txt";
    public Cadastro() {
        File diretorio = new File(DIRETORIO_USUARIOS);
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
    }

    public void cadastrarUsuario(Usuario usuario) throws NameFormatException, EmailFormatException, EmailExistException,
            AgeException, HeightFormatException {
        List<String> erros = new ArrayList<>();
        if (usuario.getNome().length()<10){
            throw new NameFormatException();
        }
        if (!usuario.getEmail().contains("@")){
            throw  new EmailFormatException();
        }
        if (emailJaCadastrado(usuario.getEmail())){
            throw new EmailExistException();
        }
        if (usuario.getIdade()<= 18){
            throw  new AgeException();
        }
        if (usuario.getAltura().contains(",")){
            throw new HeightFormatException();
        }
        File arquivoUsuario = new File(DIRETORIO_USUARIOS + "/" + usuario.getEmail() + ".txt");
        try (FileWriter writer = new FileWriter(arquivoUsuario)) {
            writer.write(usuario.toString());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o usuário: " + e.getMessage());
        }

    }
    public boolean emailJaCadastrado (String email){
        File arquivo = new File(DIRETORIO_USUARIOS + "/" + ".txt");
        return arquivo.exists();
    }

    public void salvarRespostasEmArquivo(String nomeArquivo, Usuario usuario) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("Nome: " + usuario.getNome());
            writer.newLine();

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
        File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".txt"));

        if (arquivos != null && arquivos.length > 0) {
            System.out.println("Usuários cadastrados:");
            int contador = 1;
            for (File arquivo : arquivos) {
                String nomeUsuario = arquivo.getName().replace(".txt", "");
                String nomeFormatado = formatarNome(nomeUsuario);
                String[] nomes = nomeFormatado.split(" ");
                String nomeExibido = nomes.length > 1 ? nomes[0] + " " + nomes[1] : nomes[0];
                System.out.println(contador + " - " + nomeExibido);
                contador++;
            }
        } else {
            System.out.println("Nenhum usuário cadastrado.");
        }
    }
    private String formatarNome(String nome) {
        String nomeComEspacos = nome.replaceAll("([a-z])([A-Z])", "$1 $2");
        String[] partesNome = nomeComEspacos.split(" ");
        StringBuilder nomeFinal = new StringBuilder();
        for (String parte : partesNome) {
            if (!parte.isEmpty()) {
                nomeFinal.append(parte.substring(0, 1).toUpperCase());
                nomeFinal.append(parte.substring(1).toLowerCase());
                nomeFinal.append(" ");
            }
        }
        return nomeFinal.toString().trim();
    }

    public List<String> lerFormulario() {
        List<String> perguntas = new ArrayList<>();
        File arquivoFormulario = new File(FORMULARIO_ARQUIVO);

        if (!arquivoFormulario.exists()) {
            try {

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FORMULARIO_ARQUIVO))) {
                    writer.write("Qual seu nome completo?");
                    writer.newLine();
                    writer.write("Qual sua idade?");
                    writer.newLine();
                    writer.write("Qual seu email?");
                    writer.newLine();
                    writer.write("Qual sua altura?");
                    writer.newLine();
                }
                System.out.println("Arquivo 'formulario.txt' criado com perguntas iniciais.");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo do formulário: " + e.getMessage());
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FORMULARIO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                perguntas.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo do formulário: " + e.getMessage());
        }
        return perguntas;
    }
    public void cadastrarNovaPergunta(String novaPergunta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FORMULARIO_ARQUIVO, true))) {
            writer.write(novaPergunta);
            writer.newLine();
            System.out.println("Nova Pergunta Cadastrada Com Sucesso");

        } catch (IOException e) {
            System.out.println("Erro ao Adicionar Pergunta" + e.getMessage());
        }
    }
    private List<String> lerArquivo(String caminhoArquivo) {
        List<String> perguntas = new ArrayList<>();
        System.out.println("Lendo Arquivo" + caminhoArquivo);
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
        if (indice <4){
            System.out.println("Erro não é permitido excluir as 4 primeiras perguntas");
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
            System.out.println("Número inválido. Escolha um número entre 5 e." + perguntas.size() + ".");
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
    public void buscarUsuarios(String termo) {
        File diretorio = new File(DIRETORIO_USUARIOS);
        File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".txt"));

        if (arquivos != null && arquivos.length > 0) {
            List<String> usuariosEncontrados = new ArrayList<>();

            for (File arquivo : arquivos) {
                String nomeUsuario = arquivo.getName().replace(".txt", "");

                if (nomeUsuario.toLowerCase().contains(termo.toLowerCase())) {
                    usuariosEncontrados.add(formatarNome(nomeUsuario));
                }
            }

            Collections.sort(usuariosEncontrados);
            if (!usuariosEncontrados.isEmpty()) {
                System.out.println("Usuários encontrados:");
                for (int i = 0; i < usuariosEncontrados.size(); i++) {
                    System.out.println((i + 1) + " - " + usuariosEncontrados.get(i)); // Exibe a lista de usuários
                }
            } else {
                System.out.println("Nenhum usuário encontrado para o termo de pesquisa: " + termo);
            }
        } else {
            System.out.println("Nenhum usuário cadastrado.");
        }
    }

    }


