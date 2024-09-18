package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;
import ru.semka.bookository.server.rest.dto.bookcategory.CreateBookCategoriesRequestDto;
import ru.semka.bookository.server.rest.dto.bookcategory.UpdateBookCategoriesRequestDto;
import ru.semka.bookository.server.service.CategoryService;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/categories")
@Validated
@RequiredArgsConstructor
@Tag(name = "Category", description = "Контроллер для работы с категориями книг")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получение списка всех категорий книг")
    public Collection<CategoryUiDto> getCategories() {
        return categoryService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Создание новой категории книг")
    public CategoryUiDto saveCategory(@Valid @RequestBody CreateBookCategoriesRequestDto dto) {
        return categoryService.save(dto);
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Обновление категории книг")
    public CategoryUiDto updateCategory(@PathVariable int categoryId,
                                        @Valid @RequestBody UpdateBookCategoriesRequestDto dto) {
        return categoryService.update(categoryId, dto);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Удаление категории")
    public void deleteCategory(@PathVariable int categoryId) {
        categoryService.delete(categoryId);
    }
}
