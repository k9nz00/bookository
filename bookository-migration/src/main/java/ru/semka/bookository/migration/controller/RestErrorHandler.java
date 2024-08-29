package ru.semka.bookository.migration.controller;

import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springdoc.api.ErrorMessage;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorMessage handleError(final IllegalArgumentException e) {
//        return new ErrorMessage(ExceptionUtils.getRootCauseMessage(e));
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorMessage handleError(final IllegalStateException e) {
//        return new ErrorMessage(ExceptionUtils.getRootCauseMessage(e));
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorMessage handleError(final ConstraintViolationException e) {
//        return new ErrorMessage(ExceptionUtils.getRootCause(e).getMessage());
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorMessage handleError(final MethodArgumentTypeMismatchException e) {
//        Throwable rootCause = ExceptionUtils.getRootCause(e);
//        return new ErrorMessage(rootCause.getMessage());
//    }
//
//
//    private String getErrorMessage(final BindException e) {
//        List<String> errors = new ArrayList<>(e.getBindingResult().getFieldErrors().stream()
//                .collect(Collectors.groupingBy(FieldError::getField,
//                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())))
//                .entrySet()
//                .stream()
//                .map(error -> String.format("%s : [%s]", error.getKey(), String.join(", ", error.getValue())))
//                .collect(Collectors.toUnmodifiableList()));
//        errors.add(e.getBindingResult().getAllErrors().stream()
//                .filter(error -> !(error instanceof FieldError))
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.joining("; ")));
//        return String.join("; ", errors);
//    }
}
