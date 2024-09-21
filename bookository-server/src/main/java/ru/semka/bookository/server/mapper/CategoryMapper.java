package ru.semka.bookository.server.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CategoryMapper {
    CategoryUiDto categoryEntityToDto(CategoryEntity entity);
}
