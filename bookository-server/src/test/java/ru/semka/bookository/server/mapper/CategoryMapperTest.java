package ru.semka.bookository.server.mapper;

import org.junit.jupiter.api.Test;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CategoryMapperTest {
    private final CategoryMapper mapper = new CategoryMapperImpl();

    @Test
    void categoryEntityToDto() {
        CategoryUiDto uiDto = mapper.categoryEntityToDto(getCategory());

        assertEquals(1, uiDto.id());
        assertEquals("name", uiDto.name());
    }

    @Test
    void categoryEntityToDtoWithNull() {
        CategoryUiDto uiDto = mapper.categoryEntityToDto(null);

        assertNull(uiDto);
    }

    private CategoryEntity getCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);
        categoryEntity.setName("name");

        return categoryEntity;
    }
}