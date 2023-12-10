package bg.softuni.homefurniture.exceptions;

public class OrderNotFoundException extends RuntimeException {
     public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
