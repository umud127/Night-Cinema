package az.duo.Night.Cinema.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{

    String path;

    public BadRequestException(String message, String path) {
        super(message);
        this.path = path;
    }
}
