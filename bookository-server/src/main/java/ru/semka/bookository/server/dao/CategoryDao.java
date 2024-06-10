package ru.semka.bookository.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.semka.bookository.server.dao.entity.CategoryEntity;

public interface CategoryDao extends JpaRepository<CategoryEntity, Integer> {
}
