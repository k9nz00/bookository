package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
    public CategoryEntity save(String categoryName) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(categoryName);
        entityManager.persist(entity);
        return entityManager.merge(entity);
    }

    @Override
    public void update(int categoryId, String categoryName) {
        Query query = entityManager.createNativeQuery(
                "UPDATE bookository.category SET name = :name WHERE id = :id",
                CategoryEntity.class);
        query.setParameter("name", categoryName);
        query.setParameter("id", categoryId);
        query.executeUpdate();
    }

    @Override
    public void delete(int categoryId) {
        Query nativeQuery = entityManager.createNativeQuery("DELETE FROM bookository.category WHERE id = :id");
        nativeQuery.setParameter("id", categoryId);
        nativeQuery.executeUpdate();
    }

    @Override
    public List<CategoryEntity> getAll() {
        TypedQuery<CategoryEntity> query = entityManager.createQuery(
                "SELECT e FROM CategoryEntity e ORDER BY id",
                CategoryEntity.class);
        return query.getResultList();
    }
}
