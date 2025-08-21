package az.duo.Night.Cinema.entity;

import az.duo.Night.Cinema.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity<T> {
    private boolean success;
    private StatusCode status;

    private String errorMessage;
    private T data;

    private LocalDateTime timestamp;
    private String path;

    public static <T>BaseEntity<T> ok(T data) {
        BaseEntity<T> response = new BaseEntity<>();

        response.setSuccess(true);
        response.setStatus(StatusCode.OK);

        response.setData(data);
        response.setTimestamp(LocalDateTime.now());
        response.setErrorMessage(null);

        return response;
    }

    public static <T> BaseEntity<T> notOk(StatusCode statusCode, String errorMessage, String path) {
        BaseEntity<T> response = new BaseEntity<>();

        response.setSuccess(false);
        response.setStatus(statusCode);

        response.setErrorMessage(errorMessage);
        response.setData(null);

        response.setTimestamp(LocalDateTime.now());
        response.setPath(path);

        return response;
    }
}
