package az.duo.Night.Cinema.exception;

import lombok.Getter;

@Getter
public class UnauthorizedUserException extends RuntimeException {

    String path;

    public UnauthorizedUserException(String message) {
        super(message);
    }

    public UnauthorizedUserException(String message, String path) {
        super(message);
        this.path = path;
    }

}
