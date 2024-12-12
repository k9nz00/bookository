package ru.semka.bookository.server.service;

import ru.semka.bookository.server.rest.dto.bookgenre.CreateBookGenreRequestDto;
import ru.semka.bookository.server.rest.dto.bookgenre.GenreUiDto;

import java.util.Collection;

public interface GenreService {
    void save(CreateBookGenreRequestDto dto);

    void delete(int id);

    Collection<GenreUiDto> getAll();

    GenreUiDto getById(int id);
}
