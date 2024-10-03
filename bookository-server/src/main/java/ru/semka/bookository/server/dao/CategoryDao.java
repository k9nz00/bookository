package ru.semka.bookository.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semka.bookository.server.dao.entity.CategoryEntity;

import java.util.Collection;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity, Integer> {

    Collection<CategoryEntity> findAllByIdIn(Collection<Integer> ids);
}
