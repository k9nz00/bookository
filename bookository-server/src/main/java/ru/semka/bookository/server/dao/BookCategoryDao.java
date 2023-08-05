package ru.semka.bookository.server.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.semka.bookository.server.dao.entity.CategoryEntity;

import java.util.List;

public interface BookCategoryDao {
    @Transactional
    void save(String categoryName);

    List<CategoryEntity> getAll();
}
