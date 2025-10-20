package az.duo.Night.Cinema.exception;

public class BadRequestException extends RuntimeException{

    String path;

    public BadRequestException(String message) {
        super(message);
        this.path = null;
    }

    public BadRequestException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
