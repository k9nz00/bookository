package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.BookCategoryDao;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.bookcategory.BookCategoryUiDto;
import ru.semka.bookository.server.service.BookCategoryService;
import ru.semka.bookository.server.transformers.Transformer;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryDao bookCategoryDao;
    private final Transformer<CategoryEntity, BookCategoryUiDto> bookCategoryTransformer;

    @Override
    public BookCategoryUiDto save(String categoryName) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryName);
        bookCategoryDao.save(categoryEntity);
        return bookCategoryTransformer.transform(categoryEntity);
    }

    @Override
    public CategoryEntity update(int categoryId, String categoryName) {
        return bookCategoryDao.findById(categoryId)
                .map(entity -> {
                    entity.setName(categoryName);
                    return bookCategoryDao.save(entity);
                }).orElseThrow(() -> new ResourceNotFoundException(
                        "Category with id = %d not found".formatted(categoryId))
                );
    }

    @Override
    public void delete(int categoryId) {
        bookCategoryDao.findById(categoryId)
                .ifPresentOrElse(
                        entity -> bookCategoryDao.deleteById(categoryId),
                        () -> {
                            throw new ResourceNotFoundException("Category with id = %d not found".formatted(categoryId));
                        });
    }

    @Override
    public Collection<BookCategoryUiDto> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return bookCategoryDao.findAll(sort).stream()
                .map(bookCategoryTransformer::transform)
                .toList();
    }
}
