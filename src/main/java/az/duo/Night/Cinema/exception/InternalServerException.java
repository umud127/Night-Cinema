package az.duo.Night.Cinema.exception;

public class InternalServerException extends RuntimeException {

    String path;

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, String path) {
        super(message);
        this.path = path;
    }
}
