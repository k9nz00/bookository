package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.CategoryDao;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;
import ru.semka.bookository.server.service.CategoryService;
import ru.semka.bookository.server.transformers.Transformer;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    private final Transformer<CategoryEntity, CategoryUiDto> CategoryTransformer;
    private final static String DEFAULT_SORTING_FIELD = "name";

    @Override
    public CategoryUiDto save(String categoryName) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryName);
        categoryDao.save(categoryEntity);
        return CategoryTransformer.transform(categoryEntity);
    }

    @Override
    public CategoryEntity update(int categoryId, String categoryName) {
        return categoryDao.findById(categoryId)
                .map(entity -> {
                    entity.setName(categoryName);
                    return categoryDao.save(entity);
                }).orElseThrow(() -> new ResourceNotFoundException(
                        "Category with id = %d not found".formatted(categoryId))
                );
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
        Sort sort = Sort.by(Sort.Direction.ASC, DEFAULT_SORTING_FIELD);
        return categoryDao.findAll(sort).stream()
                .map(CategoryTransformer::transform)
                .toList();
    }
}
