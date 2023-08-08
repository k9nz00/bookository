package ru.semka.bookository.server.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.rest.dto.book.CreateBookRequestDto;
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
    public Collection<BookUiDto> getBook() {
        return null;
    }

    @GetMapping("/{bookId}")
    public BookDetailsUiDto getBook(@PathVariable int bookId) {
        return bookService.getDetails(bookId);
    }

    @GetMapping("/{bookId}/book-content/{bookContentId}")
    public String getBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        return bookService.getBookContent(bookId, bookContentId);
    }

    //изменить тип возвращаемых данных
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void saveBook(@Valid CreateBookRequestDto dto,
                         @RequestPart(name = "book", required = false) MultipartFile book,
                         @RequestPart(name = "bookCover", required = false) MultipartFile bookCover) throws IOException {
        bookService.save(dto, book, bookCover);
    }

//    @PutMapping(name = "/{bookId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public void updateBook(@PathVariable int bookId,
//                           @Valid CreateBookRequestDto dto,
//                           @RequestPart(name = "book", required = false) MultipartFile book,
//                           @RequestPart(name = "bookCover", required = false) MultipartFile bookCover) throws IOException {
//        //bookService.save(dto, book, bookCover);
//    }

    /*
     * Добавить новые эндпоинты
     * - добавление новой книги к карточке книги
     * - Удаление книги от карточки книги
     * - Замена обложки у книги
     * - Удаление обложки у карточки
     * - Удаление карточки книги (каскадное -  удаление карточки и всего контента относящегося к ней)
     * */
}
