package ru.semka.bookository.server.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiErrorResponseDto {
    private Integer code;
    private String message;
    private Object body;
}