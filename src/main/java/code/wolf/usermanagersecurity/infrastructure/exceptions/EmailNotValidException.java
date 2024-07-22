package code.wolf.usermanagersecurity.infrastructure.exceptions;

public class EmailNotValidException extends RuntimeException{
    public EmailNotValidException(String message) {
        super(message);
    }
}
