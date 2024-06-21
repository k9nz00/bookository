package ru.semka.bookository.server.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorValidationDto {
    private String field;
    private Object providedValue;
    private String message;
}
