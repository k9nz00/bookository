package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookService;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    @Operation(description = "Создание карточки книги")
    @ResponseStatus(HttpStatus.OK)
    public BookUiDto saveBook(@Valid @RequestBody BookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PostMapping("/saveFromFile")
    @Operation(description = "Получение подробной карточки книги")
    public Integer saveFromFile(@RequestPart(name = "file") MultipartFile file) {
        return bookService.saveFromFile(file);
    }

    @GetMapping
    @Operation(description = "Получение списка карточек книг")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookUiDto> getBooks(@ParameterObject @Valid final BookCriteriaDto criteriaDto) {
        return bookService.getList(criteriaDto);
    }

    @GetMapping("/{bookId}")
    @Operation(description = "Получение подробной карточки книги")
    @ResponseStatus(HttpStatus.OK)
    public BookDetailsUiDto getBook(@PathVariable int bookId) {
        return bookService.getDetails(bookId);
    }

    @PutMapping("/{bookId}")
    @Operation(description = "Обновление карточки книги")
    @ResponseStatus(HttpStatus.OK)
    public BookUiDto updateBook(@PathVariable int bookId,
                                @Valid @RequestBody BookRequestDto dto) {
        return bookService.update(bookId, dto);
    }

    @DeleteMapping("/{bookId}")
    @Operation(description = "Удаление карточки книги и всего что к ней привязано - обложки, и всех книжных файлов")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int bookId) {
        bookService.delete(bookId);
    }
}
