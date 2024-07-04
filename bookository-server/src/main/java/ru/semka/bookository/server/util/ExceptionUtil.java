package ru.semka.bookository.server.util;

import lombok.experimental.UtilityClass;
import org.springframework.validation.BindException;
import ru.semka.bookository.server.common.dto.ErrorValidationDto;

import java.util.Collection;
import java.util.stream.Collectors;

@UtilityClass
public class ExceptionUtil {
    public static Collection<ErrorValidationDto> getValidationErrors(BindException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorValidationDto(
                        fieldError.getField(),
                        fieldError.getRejectedValue(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());
    }
}
