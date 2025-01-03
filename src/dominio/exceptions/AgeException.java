package dominio.exceptions;

public class AgeException extends Throwable {
    public AgeException() {
        super("Proibido o dominio.repositorio.Cadastro de menores de idade");
    }
}
