package az.duo.Night.Cinema.handler;

import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.exception.BadRequestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseEntity<String> handleException(Exception e) {
        return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage(), null);
    }

    @ExceptionHandler(BadRequestException.class)
    public BaseEntity<String> handleBadRequestException(BadRequestException e) {
        return BaseEntity.notOk(StatusCode.BAD_REQUEST, e.getMessage(), null);
    }
}
