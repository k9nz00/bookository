package ru.semka.bookository.server.util;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.semka.bookository.server.common.dto.ErrorValidationDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExceptionUtilTest {

    @Test
    void getValidationErrors() {

        List<ErrorValidationDto> validationErrors = (List<ErrorValidationDto>) ExceptionUtil.getValidationErrors(getException());

        assertFalse(validationErrors.isEmpty());
        assertEquals(1, validationErrors.size());

        ErrorValidationDto errorValidationDto = validationErrors.get(0);
        assertEquals("fieldName", errorValidationDto.getField());
        assertEquals("rejectedValue", errorValidationDto.getProvidedValue());
        assertEquals("defaultMessage", errorValidationDto.getMessage());
    }

    private BindException getException() {
        FieldError fieldError = mock(FieldError.class);
        BindException exception = mock(BindException.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(fieldError.getField()).thenReturn("fieldName");
        when(fieldError.getRejectedValue()).thenReturn("rejectedValue");
        when(fieldError.getDefaultMessage()).thenReturn("defaultMessage");

        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        when(exception.getBindingResult()).thenReturn(bindingResult);

        return exception;
    }
}