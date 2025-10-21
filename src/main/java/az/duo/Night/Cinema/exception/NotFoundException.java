package az.duo.Night.Cinema.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    String path;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String path) {
        super(message);
        this.path = path;
    }

}
