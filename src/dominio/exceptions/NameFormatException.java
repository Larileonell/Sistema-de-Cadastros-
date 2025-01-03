package dominio.exceptions;

public class NameFormatException extends Throwable {
    public NameFormatException() {
        super("Número de caracteres inválido, o nome deve possuir  10 e ou mais.");
    }
}
