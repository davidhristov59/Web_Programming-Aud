package mk.ukim.finki.wp_aud1.model.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("User already exists exception");
    }
}
