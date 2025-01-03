package dominio.exceptions;

public class EmailExistException  extends Throwable{
    public EmailExistException() {
        super("E-mail já cadastrado em nosso sistema");
    }
}
