package ru.semka.bookository.server.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.semka.bookository.server.dao.entity.CategoryEntity;

import java.util.List;

public interface BookCategoryDao {
    @Transactional
    CategoryEntity save(String categoryName);

    @Transactional
    void update(int categoryId, String categoryName);

    @Transactional
    void delete(int categoryId);

    List<CategoryEntity> getAll();
}
