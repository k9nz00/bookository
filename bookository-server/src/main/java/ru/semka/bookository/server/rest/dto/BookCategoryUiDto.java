package ru.semka.bookository.server.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCategoryUiDto {
    private final Integer id;
    private final String name;
}
