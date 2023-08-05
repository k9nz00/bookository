package ru.semka.bookository.server.dao;

import ru.semka.bookository.server.dao.entity.CategoryEntity;

import java.util.List;

public interface BookCategoryDao {
    void save(String categoryName);

    List<CategoryEntity> getAll();
}
