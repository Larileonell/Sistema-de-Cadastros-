package dominio.entidade.usuario;

public class Usuario {
    private String nome;
    private String email;
    private int idade;
    private String altura;

    public Usuario(String nome, String email, int idade, String altura) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    public String getAltura() {
        return altura;
    }
    public void exibirInformacoes() {
        System.out.println(nome);
        System.out.println(email);
        System.out.println(idade);
        System.out.println(altura);
    }

    @Override
    public String toString() {
        return "dominio.entidade.usuario.Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                ", altura=" + altura +
                '}';
    }
}
