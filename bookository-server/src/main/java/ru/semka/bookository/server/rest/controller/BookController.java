package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public Collection<BookUiDto> getBooks(@Valid @ParameterObject final BookCriteriaDto criteriaDto) {
        return bookService.getBooks(criteriaDto);
    }

    @GetMapping("/{bookId}")
    public BookDetailsUiDto getBook(@PathVariable int bookId) {
        return bookService.getDetails(bookId);
    }

    @GetMapping("/{bookId}/book-content/{bookContentId}")
    public String getBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        return bookService.getBookContent(bookId, bookContentId);
    }

    @PostMapping(
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public void saveBook(@Valid BookRequestDto dto,
                         @RequestPart(name = "book", required = false) MultipartFile book,
                         @RequestPart(name = "cover", required = false) MultipartFile cover) throws IOException {
        bookService.save(dto, book, cover);
    }

    @PutMapping("{bookId}/attach")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Added new book-content to the book")
    public void attachBook(@PathVariable int bookId,
                           @RequestPart(name = "book") MultipartFile book) throws IOException {
        bookService.attachBook(bookId, book);
    }

    @PutMapping("/{bookId}")
    public BookUiDto updateCardBook(@PathVariable int bookId,
                                    @Valid @RequestBody BookRequestDto dto) {
        return bookService.update(bookId, dto);
    }

    @DeleteMapping("/{bookId}/book-content/{bookContentId}")
    public void deleteBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        bookService.deleteBookContent(bookId, bookContentId);
    }

    @PutMapping("/{bookId}")
    public void updateBookCover(@PathVariable int bookId,
                                @RequestPart(name = "cover") MultipartFile cover) throws IOException {
        bookService.updateBookCover(bookId, cover);
    }

    /*
     * Добавить новые эндпоинты
     * - добавление новой книги к карточке книги - ok
     * - обновление карточки - ok
     * - Удаление книги от карточки книги - ok
     * - Замена обложки у книги - ok
     * - Удаление обложки у карточки - ok
     * - Удаление карточки книги (каскадное -  удаление карточки и всего контента относящегося к ней)
     * */
}
