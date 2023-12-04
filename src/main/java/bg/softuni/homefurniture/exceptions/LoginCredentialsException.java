package bg.softuni.homefurniture.exceptions;

public class LoginCredentialsException extends IllegalArgumentException {
    public LoginCredentialsException(String message) {
        super(message);
    }
}
