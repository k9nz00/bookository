package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.CategoryDao;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.mapper.CategoryMapper;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;
import ru.semka.bookository.server.rest.dto.bookcategory.CreateBookCategoriesRequestDto;
import ru.semka.bookository.server.rest.dto.bookcategory.UpdateBookCategoriesRequestDto;
import ru.semka.bookository.server.service.CategoryService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;
    private final static String DEFAULT_SORTING_FIELD = "name";

    @Override
    public CategoryUiDto save(CreateBookCategoriesRequestDto dto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(dto.getName());
        categoryDao.save(categoryEntity);
        return categoryMapper.categoryEntityToDto(categoryEntity);
    }

    @Override
    public CategoryUiDto update(int categoryId, UpdateBookCategoriesRequestDto dto) {
        return categoryDao.findById(categoryId)
                .map(entity -> {
                    entity.setName(dto.getName());
                    categoryDao.save(entity);
                    return categoryMapper.categoryEntityToDto(entity);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Category with id = %d not found".formatted(categoryId)));
    }

    @Override
    public void delete(int categoryId) {
        categoryDao.findById(categoryId)
                .ifPresentOrElse(
                        entity -> categoryDao.deleteById(categoryId),
                        () -> {
                            throw new ResourceNotFoundException("Category with id = %d not found".formatted(categoryId));
                        });
    }

    @Override
    public Collection<CategoryUiDto> getAll() {
        return categoryDao.findAll(Sort.by(Sort.Direction.ASC, DEFAULT_SORTING_FIELD)).stream()
                .map(categoryMapper::categoryEntityToDto)
                .toList();
    }
}
