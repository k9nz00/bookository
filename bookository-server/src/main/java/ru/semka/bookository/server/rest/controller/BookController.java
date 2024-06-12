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
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public void saveBook(
            @RequestPart(value = "name") String name,
            @RequestPart(value = "author", required = false) String author,
            @RequestPart(value = "genre", required = false) String genre,
            @RequestPart(value = "language", required = false) String language,
            @RequestPart(value = "annotation", required = false) String annotation,
            @RequestPart(value = "categories", required = false) Integer[] categories,
            @RequestPart(name = "book", required = false) MultipartFile book,
            @RequestPart(name = "cover", required = false) MultipartFile cover) throws IOException {
        BookRequestDto bookDto = getBookDto(name, author, genre, language, annotation, categories);
        bookService.save(bookDto, book, cover);
    }

    @GetMapping
    public Collection<BookUiDto> getBooks(@Valid @ParameterObject final BookCriteriaDto criteriaDto) {
        return bookService.getBooks(criteriaDto);
    }

    @GetMapping("/{bookId}")
    public BookDetailsUiDto getBook(@PathVariable int bookId) {
        return bookService.getDetails(bookId);
    }

    @PutMapping("/{bookId}")
    public BookUiDto updateBook(@PathVariable int bookId,
                                @Valid @RequestBody BookRequestDto dto) {
        return bookService.update(bookId, dto);
    }

    @GetMapping("/{bookId}/book-content/{bookContentId}")
    public String getBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        return bookService.getBookContent(bookId, bookContentId);
    }

    @PutMapping("{bookId}/attach")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Added new book-content to the book")
    public void attachBook(@PathVariable int bookId,
                           @RequestPart(name = "book") MultipartFile book) throws IOException {
        bookService.attachBook(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable int bookId) {
        bookService.deleteBook(bookId);
    }

    @DeleteMapping("/{bookId}/book-content/{bookContentId}")
    public void deleteBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        bookService.deleteBookContent(bookId, bookContentId);
    }

    @PutMapping("/{bookId}/cover")
    public void updateBookCover(@PathVariable int bookId,
                                @RequestPart(name = "cover") MultipartFile cover) throws IOException {
        bookService.updateBookCover(bookId, cover);
    }

    @DeleteMapping("/{bookId}/cover")
    public void deleteBookCover(@PathVariable int bookId) {
        bookService.deleteBookCover(bookId);
    }

    private BookRequestDto getBookDto(String name,
                                      String author,
                                      String genre,
                                      String language,
                                      String annotation,
                                      Integer[] categories) {
        return new BookRequestDto(
                name,
                author,
                genre,
                language != null ? Language.fromValue(language.toUpperCase()) : null,
                annotation,
                categories != null ? List.of(categories) : null
        );
    }
}
