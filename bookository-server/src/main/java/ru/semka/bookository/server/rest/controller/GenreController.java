package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.semka.bookository.server.rest.dto.bookgenre.CreateBookGenreRequestDto;
import ru.semka.bookository.server.rest.dto.bookgenre.GenreUiDto;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/genres")
@Tag(name = "Genre", description = "Контроллер для работы с жанрами книг")
public class GenreController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получение списка жанров книг")
    public Collection<GenreUiDto> getGenres() {
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получение жанра по id")
    public GenreUiDto getGenre(@PathVariable int id) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Создание жанра")
    public void createGenre(@RequestBody CreateBookGenreRequestDto dto) {

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Удаление жанра")
    public void deleteGenre(@PathVariable int id) {

    }
}
