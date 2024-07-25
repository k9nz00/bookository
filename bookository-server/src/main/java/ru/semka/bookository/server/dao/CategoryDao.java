package ru.semka.bookository.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semka.bookository.server.dao.entity.CategoryEntity;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity, Integer> {
}
