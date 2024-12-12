package ru.semka.bookository.server.rest.dto.bookgenre;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GenreUiDto {
    private final Integer id;
    private final String name;
}
