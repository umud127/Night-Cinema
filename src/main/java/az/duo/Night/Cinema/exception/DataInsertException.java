package az.duo.Night.Cinema.exception;

import lombok.Getter;

@Getter
public class DataInsertException extends RuntimeException {

    String path;

    public DataInsertException(String message, String path) {
        super(message);
        this.path = path;
    }
}
