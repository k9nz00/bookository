package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.dao.entity.BookEntity;
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

    @PostMapping
    public BookEntity saveBook(@Valid @RequestBody BookRequestDto requestDto) throws IOException {
        return bookService.save(requestDto);
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
}
