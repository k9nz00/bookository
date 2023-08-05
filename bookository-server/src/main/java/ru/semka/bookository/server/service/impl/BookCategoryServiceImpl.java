package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.semka.bookository.server.dao.BookCategoryDao;
import ru.semka.bookository.server.rest.dto.BookCategoryUiDto;
import ru.semka.bookository.server.service.BookCategoryService;

import java.util.Collection;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryDao bookCategoryDao;

    @Override
    public void save(String categoryName) {
        bookCategoryDao.save(categoryName);
    }

    @Override
    public Collection<BookCategoryUiDto> getAll() {
        return bookCategoryDao.getAll().stream()
                .map(entity -> new BookCategoryUiDto(entity.getId(), entity.getName()))
                .sorted(Comparator.comparing(BookCategoryUiDto::getId))
                .toList();
    }
}
