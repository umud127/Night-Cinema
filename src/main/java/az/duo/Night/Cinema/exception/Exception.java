package az.duo.Night.Cinema.exception;

import lombok.Getter;

@Getter
public class Exception extends RuntimeException{

    String path;

    public Exception(String message, String path) {
        super(message);
        this.path = path;
    }
}