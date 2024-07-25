package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.cover.CoverDto;
import ru.semka.bookository.server.service.BookCoverService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@RequiredArgsConstructor
@Tag(name = "BookCover", description = "Контроллер для работы с обложками книг")
public class BookCoverController {
    private final BookCoverService bookCoverService;

    @PostMapping("/{bookId}/cover")
    @Operation(description = "Замена обложки у карточки книги. Старая будет удалена")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveBookCover(@PathVariable int bookId,
                              @RequestPart(name = "cover") MultipartFile cover) throws IOException {
        bookCoverService.save(bookId, cover);
    }

    @GetMapping("/{bookId}/cover")
    @Operation(description = "Получение обложки книги. Возвращает строку в base64 кодировании")
    @ResponseStatus(HttpStatus.OK)
    public CoverDto getBookCover(@PathVariable int bookId) {
        return new CoverDto(bookCoverService.get(bookId));
    }

    @DeleteMapping("/{bookId}/cover")
    @Operation(description = "Удаление обложки у карточки")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookCover(@PathVariable int bookId) {
        bookCoverService.delete(bookId);
    }
}
