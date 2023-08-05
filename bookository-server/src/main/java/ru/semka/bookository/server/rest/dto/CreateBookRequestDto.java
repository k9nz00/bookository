package ru.semka.bookository.server.rest.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import ru.semka.bookository.server.common.enums.Language;

@Data
@Validated
public class CreateBookRequestDto {
    private String name;
    private String author;
    private String genre;
    private Language language;
    private String annotation;
    private String description;
    private int[] categories;
}