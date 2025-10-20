package az.duo.Night.Cinema.exception;

public class NotFoundException extends RuntimeException {

    String path;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
