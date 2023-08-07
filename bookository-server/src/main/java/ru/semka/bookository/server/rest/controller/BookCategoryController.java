package ru.semka.bookository.server.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.semka.bookository.server.rest.dto.bookcategory.BookCategoryUiDto;
import ru.semka.bookository.server.rest.dto.bookcategory.CreateBookCategoriesRequestDto;
import ru.semka.bookository.server.rest.dto.bookcategory.UpdateBookCategoriesRequestDto;
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
    public BookCategoryUiDto save(@Valid @RequestBody CreateBookCategoriesRequestDto dto) {
        return bookCategoryService.save(dto.getName());
    }

    @PutMapping("/{categoryId}")
    public void update(@PathVariable int categoryId,
                       @Valid @RequestBody UpdateBookCategoriesRequestDto dto) {
        bookCategoryService.update(categoryId, dto.getName());
    }
}
