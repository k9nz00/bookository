package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.semka.bookository.server.dao.BookCategoryDao;
import ru.semka.bookository.server.dao.entity.CategoryEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookCategoryDaoImpl implements BookCategoryDao {
    private final EntityManager entityManager;

    @Override
    public void save(String categoryName) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(categoryName);
        entityManager.persist(entity);
        entityManager.merge(entity);
    }

    @Override
    public List<CategoryEntity> getAll() {
        Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM bookository.category ORDER BY id", CategoryEntity.class);
        return nativeQuery.getResultList();
    }
}
