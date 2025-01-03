package dominio.exceptions;

public class HeightFormatException extends Throwable{
    public HeightFormatException() {
        super("Erro na passagem do parametro altura, falta a vírgula. ex: 1,80");
    }
}
