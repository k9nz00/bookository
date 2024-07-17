package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.service.BookService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookCoverService bookCoverService;

    @PostMapping
    @Operation(description = "Создание карточки книги")
    @ResponseStatus(HttpStatus.OK)
    public BookUiDto saveBook(@Valid @RequestBody BookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @GetMapping
    @Operation(description = "Получение списка карточек книг")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookUiDto> getBooks(@Valid final BookCriteriaDto criteriaDto) {
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

    @DeleteMapping("/{bookId}")
    @Operation(description = "Удаление карточки книги и всего что к ней привязано - обложки, и всех книжных файлов")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int bookId) {
        bookService.deleteBook(bookId);
    }


    //-----------------

    @PostMapping("{bookId}/content")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Прикрепление контента книги к карточке")
    public int saveBookContent(@PathVariable int bookId,
                               @RequestPart(name = "book") MultipartFile book) throws IOException {
        return bookService.saveBookContent(bookId, book);
    }

    @GetMapping("/{bookId}/content/{bookContentId}")
    @Operation(description = "Получение контента конкретной книги, прикрепленной к карточке")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> getBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        return bookService.getBookContent(bookId, bookContentId);
    }

    @DeleteMapping("/content/{bookContentId}")
    @Operation(description = "Удаление книжного файла, прикреаленного к карточке книги")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookContent(@PathVariable int bookContentId) {
        bookService.deleteBookContent(bookContentId);
    }

    //------------------------

    @PostMapping("/{bookId}/cover")
    @Operation(description = "Замена обложки у карточки книги. Старая будет удалена")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveBookCover(@PathVariable int bookId,
                              @RequestPart(name = "cover") MultipartFile cover) throws IOException {
        bookService.saveBookCover(bookId, cover);
    }

    @GetMapping("/cover/{coverId}")
    @Operation(description = "Замена обложки у карточки книги. Старая будет удалена")
    @ResponseStatus(HttpStatus.OK)
    public String getBookCover(@PathVariable int coverId) {
        return bookCoverService.get(coverId);
    }

    @DeleteMapping("/{bookId}/cover")
    @Operation(description = "Удаление обложки у карточки")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookCover(@PathVariable int bookId) {
        bookService.deleteBookCover(bookId);
    }
}
