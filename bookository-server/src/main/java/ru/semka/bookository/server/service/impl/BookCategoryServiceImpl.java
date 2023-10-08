package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        CategoryEntity entity = bookCategoryDao.save(categoryName);
        return bookCategoryTransformer.transform(entity);
    }

    @Override
    public void update(int categoryId, String categoryName) {
        bookCategoryDao.update(categoryId, categoryName);
    }

    @Override
    public void delete(int categoryId) {
        bookCategoryDao.delete(categoryId);
    }

    @Override
    public Collection<BookCategoryUiDto> getAll() {
        return bookCategoryDao.getAll().stream()
                .map(bookCategoryTransformer::transform)
                .toList();
    }
}
