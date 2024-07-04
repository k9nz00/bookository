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
    @Operation(description = "Создание карточки книги")
    @ResponseStatus(HttpStatus.OK)
    public BookEntity saveBook(@Valid @RequestBody BookRequestDto requestDto) throws IOException {
        return bookService.save(requestDto);
    }

    @GetMapping
    @Operation(description = "Создание списка карточек книг")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookUiDto> getBooks(@Valid @ParameterObject final BookCriteriaDto criteriaDto) {
        return bookService.getBooks(criteriaDto);
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

    @GetMapping("/{bookId}/book-content/{bookContentId}")
    @Operation(description = "Получение контента конкретной книги, прикрепленной к карточке")
    @ResponseStatus(HttpStatus.OK)
    public String getBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        return bookService.getBookContent(bookId, bookContentId);
    }

    @PutMapping("{bookId}/attach")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Прикрепление контента книги к карточке")
    public void attachBook(@PathVariable int bookId,
                           @RequestPart(name = "book") MultipartFile book) throws IOException {
        bookService.attachBook(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    @Operation(description = "Удаление карточки книги и всего что к ней привязано - обложки, и всех книжных файлов")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int bookId) {
        bookService.deleteBook(bookId);
    }

    @DeleteMapping("/{bookId}/book-content/{bookContentId}")
    @Operation(description = "Удаление книжного файла, прикреаленного к карточке книги")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        bookService.deleteBookContent(bookId, bookContentId);
    }

    @PutMapping("/{bookId}/cover")
    @Operation(description = "Изменение обложки к карточки книги. Старая будет удалена")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBookCover(@PathVariable int bookId,
                                @RequestPart(name = "cover") MultipartFile cover) throws IOException {
        bookService.updateBookCover(bookId, cover);
    }

    @DeleteMapping("/{bookId}/cover")
    @Operation(description = "Удаление обложки у карточки")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookCover(@PathVariable int bookId) {
        bookService.deleteBookCover(bookId);
    }
}
