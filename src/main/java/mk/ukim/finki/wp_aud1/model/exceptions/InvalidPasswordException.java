package mk.ukim.finki.wp_aud1.model.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("Invalid password exception");
    }
}
