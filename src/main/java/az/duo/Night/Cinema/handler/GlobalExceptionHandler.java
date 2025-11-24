package az.duo.Night.Cinema.handler;

import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.exception.*;
import az.duo.Night.Cinema.exception.Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseEntity<String> handleException(Exception e, String path) {
        return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, e.getMessage(), path);
    }

    @ExceptionHandler(BadRequestException.class)
    public BaseEntity<String> handleBadRequestException(BadRequestException e) {
        return BaseEntity.notOk(StatusCode.BAD_REQUEST, e.getMessage(), e.getPath());
    }

    @ExceptionHandler(NotFoundException.class)
    public BaseEntity<String> handleNotFoundException(NotFoundException e) {
        return BaseEntity.notOk(StatusCode.NOT_FOUND, e.getMessage(), e.getPath());
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public BaseEntity<String> handleUnauthorizedUserException(UnauthorizedUserException e) {
        return BaseEntity.notOk(StatusCode.UNAUTHORIZED, e.getMessage(), e.getPath());
    }

    @ExceptionHandler(DataInsertException.class)
    public BaseEntity<String> handleDataInsertException(DataInsertException e) {
        return BaseEntity.notOk(StatusCode.CONFLICT, e.getMessage(), e.getPath());
    }

    @ExceptionHandler(UnmatchedDataException.class)
    public BaseEntity<String> handleUnmatchedDataException(UnmatchedDataException e) {
        return BaseEntity.notOk(StatusCode.CONFLICT, e.getMessage(), e.getPath());
    }
}
