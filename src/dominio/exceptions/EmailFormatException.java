package dominio.exceptions;

public class EmailFormatException extends  Throwable{
    public EmailFormatException() {
        super("E-mail sem o \"@\", adicione para prosseguir ");
    }
}
