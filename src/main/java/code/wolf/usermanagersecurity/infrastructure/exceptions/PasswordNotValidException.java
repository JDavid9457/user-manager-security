package code.wolf.usermanagersecurity.infrastructure.exceptions;

public class PasswordNotValidException extends RuntimeException{
    public PasswordNotValidException(String message) {
        super(message);
    }
}
