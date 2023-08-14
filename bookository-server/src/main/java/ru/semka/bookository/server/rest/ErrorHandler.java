package ru.semka.bookository.server.rest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.semka.bookository.server.rest.dto.ApiErrorResponseDto;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto handleError(final IllegalStateException e) {
        return new ApiErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ExceptionUtils.getRootCause(e).getMessage());
    }
}
