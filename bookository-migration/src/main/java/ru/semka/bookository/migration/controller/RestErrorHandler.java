package ru.semka.bookository.migration.controller;

import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.semka.bookository.migration.exeption.MigrationException;

@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleError(final IllegalArgumentException e) {
        return new ErrorMessage(ExceptionUtils.getRootCauseMessage(e));
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleError(final IllegalStateException e) {
        return new ErrorMessage(ExceptionUtils.getRootCauseMessage(e));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleError(final ConstraintViolationException e) {
        return new ErrorMessage(ExceptionUtils.getRootCause(e).getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleError(final MethodArgumentTypeMismatchException e) {
        Throwable rootCause = ExceptionUtils.getRootCause(e);
        return new ErrorMessage(rootCause.getMessage());
    }

    @ExceptionHandler(MigrationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleError(final MigrationException e) {
        Throwable rootCause = ExceptionUtils.getRootCause(e);
        return new ErrorMessage(rootCause.getMessage());
    }
}
