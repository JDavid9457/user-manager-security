package code.wolf.usermanagersecurity.infrastructure.exceptions;

public class EmailDuplicatedException extends RuntimeException{
    public EmailDuplicatedException(String message) {
        super(message);
    }
}
