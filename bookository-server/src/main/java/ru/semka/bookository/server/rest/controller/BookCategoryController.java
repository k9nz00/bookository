package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
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
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookCategoryUiDto> getCategories() {
        return bookCategoryService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookCategoryUiDto saveCategory(@Valid @RequestBody CreateBookCategoriesRequestDto dto) {
        return bookCategoryService.save(dto.getName());
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CategoryEntity updateCategory(@PathVariable int categoryId,
                                         @Valid @RequestBody UpdateBookCategoriesRequestDto dto) {
        return bookCategoryService.update(categoryId, dto.getName());
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Remove category")
    public void deleteCategory(@PathVariable int categoryId) {
        bookCategoryService.delete(categoryId);
    }
}
