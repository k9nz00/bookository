package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.service.BookContentService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@RequiredArgsConstructor
@Tag(name = "BookContent", description = "Контроллер для работы с контентом книг (книжными файлами)")
public class BookContentController {
    private final BookContentService bookContentService;

    @PostMapping("{bookId}/content")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Прикрепление контента книги к карточке")
    public int saveBookContent(@PathVariable int bookId,
                               @RequestPart(name = "book") MultipartFile book) throws IOException {
        return bookContentService.save(bookId, book);
    }

    @GetMapping("/{bookId}/content/{bookContentId}")
    @Operation(description = "Получение контента конкретной книги, прикрепленной к карточке")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> getBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        return bookContentService.get(bookId, bookContentId);
    }

    @DeleteMapping("/{bookId}/content/{bookContentId}")
    @Operation(description = "Удаление книжного файла, прикрепленного к карточке книги")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookContent(@PathVariable int bookId, @PathVariable int bookContentId) {
        bookContentService.delete(bookId, bookContentId);
    }
}
