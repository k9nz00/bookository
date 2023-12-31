package ru.semka.bookository.server.rest;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.rest.dto.ApiErrorResponseDto;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto handleError(final IllegalStateException e) {
        return new ApiErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                ExceptionUtils.getRootCause(e).getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto handleError(final IllegalArgumentException e) {
        return new ApiErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                ExceptionUtils.getRootCause(e).getMessage());
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto handleError(final NotImplementedException e) {
        return new ApiErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ExceptionUtils.getRootCause(e).getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponseDto handleError(final ResourceNotFoundException e) {
        return new ApiErrorResponseDto(HttpStatus.NOT_FOUND.value(), ExceptionUtils.getRootCause(e).getMessage());
    }
}
