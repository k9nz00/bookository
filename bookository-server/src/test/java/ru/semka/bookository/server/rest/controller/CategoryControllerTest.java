package ru.semka.bookository.server.rest.controller;

import org.junit.jupiter.api.Test;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;
import ru.semka.bookository.server.rest.dto.bookcategory.CreateBookCategoriesRequestDto;
import ru.semka.bookository.server.rest.dto.bookcategory.UpdateBookCategoriesRequestDto;
import ru.semka.bookository.server.service.CategoryService;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {
    private final CategoryService service = mock(CategoryService.class);
    private final CategoryController controller = new CategoryController(service);

    @Test
    void getCategories() {
        Set<CategoryUiDto> categoryUiDtoSet = Collections.singleton(mock(CategoryUiDto.class));
        when(service.getAll()).thenReturn(categoryUiDtoSet);

        Collection<CategoryUiDto> categories = controller.getCategories();

        assertEquals(categoryUiDtoSet, categories);
        verify(service).getAll();
    }

    @Test
    void saveCategory() {
        String categoryName = "name";
        CreateBookCategoriesRequestDto requestDto = new CreateBookCategoriesRequestDto();
        requestDto.setName(categoryName);
        CategoryUiDto uiDto = mock(CategoryUiDto.class);
        when(service.save(requestDto)).thenReturn(uiDto);

        CategoryUiDto savedCategory = controller.saveCategory(requestDto);

        assertEquals(uiDto, savedCategory);
        verify(service).save(argThat(arg -> {
            assertEquals(categoryName, arg.getName());
            return true;
        }));
    }

    @Test
    void updateCategory() {
        int categoryId = 1;
        String categoryName = "name";
        UpdateBookCategoriesRequestDto requestDto = new UpdateBookCategoriesRequestDto();
        requestDto.setName(categoryName);
        CategoryUiDto uiDto = mock(CategoryUiDto.class);

        when(service.update(categoryId, requestDto)).thenReturn(uiDto);

        CategoryUiDto updatedCategory = controller.updateCategory(categoryId, requestDto);
        assertEquals(uiDto, updatedCategory);
        verify(service).update(
                eq(categoryId),
                argThat(arg -> {
                    assertEquals(categoryName, arg.getName());
                    return true;
                }));
    }

    @Test
    void deleteCategory() {
        int categoryId = 1;
        doNothing().when(service).delete(anyInt());

        controller.deleteCategory(categoryId);
        verify(service).delete(categoryId);
    }
}