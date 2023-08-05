package ru.semka.bookository.server.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.semka.bookository.server.rest.dto.BookCategoryUiDto;
import ru.semka.bookository.server.rest.dto.CreateBookCategoriesRequestDto;
import ru.semka.bookository.server.service.BookCategoryService;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/book-categories")
@Validated
@RequiredArgsConstructor
public class BookCategoryController {
    private final BookCategoryService bookCategoryService;

    @GetMapping
    public Collection<BookCategoryUiDto> getBooks() {
        return bookCategoryService.getAll();
    }

    @PostMapping
    public void save(@Valid @RequestBody CreateBookCategoriesRequestDto dto) {
        bookCategoryService.save(dto.getName());
    }
}
