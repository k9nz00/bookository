package ru.semka.bookository.server.rest.dto.bookcategory;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class UpdateBookCategoriesRequestDto {
    private String name;
}