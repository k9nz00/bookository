package ru.semka.bookository.server.rest.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CreateBookCategoriesRequestDto {
    private String name;
}