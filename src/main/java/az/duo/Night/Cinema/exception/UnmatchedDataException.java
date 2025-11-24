package az.duo.Night.Cinema.exception;

import lombok.Getter;

@Getter
public class UnmatchedDataException extends RuntimeException {

    String path;

    public UnmatchedDataException(String message, String path) {
        super(message);
        this.path = path;
    }
}
