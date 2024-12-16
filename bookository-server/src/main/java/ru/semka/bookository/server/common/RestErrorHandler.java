package ru.semka.bookository.server.common;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.semka.bookository.server.common.dto.ApiErrorResponseDto;
import ru.semka.bookository.server.common.dto.ErrorValidationDto;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.util.ExceptionUtil;

import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Collection<ErrorValidationDto> validationErrors = ExceptionUtil.getValidationErrors(ex);
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(
                status.value(),
                "Validation error",
                validationErrors
        );
        return new ResponseEntity(apiErrorResponseDto, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        String message = ex.getMessage();
        Object providedValue = null;
        String filedName = null;

        if (ex.getRootCause() instanceof InvalidFormatException) {
            InvalidFormatException rootCause = (InvalidFormatException) ex.getRootCause();
            providedValue = rootCause.getValue();
            filedName = rootCause.getPath().stream()
                    .findFirst()
                    .map(JsonMappingException.Reference::getFieldName)
                    .orElseThrow(() ->
                            new IllegalStateException("not found field")
                    );
        }
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(
                status.value(),
                "Validation error",
                List.of(new ErrorValidationDto(filedName, providedValue, message))
        );
        return new ResponseEntity(apiErrorResponseDto, headers, status);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto handleError(final IllegalStateException e) {
        return new ApiErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                ExceptionUtils.getRootCause(e).getMessage(),
                null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto handleError(final IllegalArgumentException e) {
        return new ApiErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                ExceptionUtils.getRootCause(e).getMessage(),
                null);
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto handleError(final NotImplementedException e) {
        return new ApiErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                ExceptionUtils.getRootCause(e).getMessage(),
                null);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponseDto handleError(final ResourceNotFoundException e) {
        return new ApiErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                ExceptionUtils.getRootCause(e).getMessage(),
                null);
    }
}
