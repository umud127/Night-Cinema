package az.duo.Night.Cinema.exception;

import lombok.Getter;

@Getter
public class InternalServerException extends RuntimeException {

    String path;

    public InternalServerException(String message, String path) {
        super(message);
        this.path = path;
    }
}
