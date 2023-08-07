package ru.semka.bookository.server.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.rest.dto.book.CreateBookRequestDto;
import ru.semka.bookository.server.service.BookService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{bookId}")
    public BookUiDto getBook(@PathVariable int bookId) {
        return bookService.getDetails(bookId);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void saveBook(@Valid CreateBookRequestDto dto,
                         @RequestPart(name = "book", required = false) MultipartFile book,
                         @RequestPart(name = "bookCover", required = false) MultipartFile bookCover) throws IOException {
        bookService.save(dto, book, bookCover);
    }
}
