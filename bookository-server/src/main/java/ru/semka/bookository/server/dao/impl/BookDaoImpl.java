package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.CreateBookRequestDto;
import ru.semka.bookository.server.util.CommonUtil;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private final EntityManager entityManager;

    @Override
    public BookEntity save(CreateBookRequestDto dto) {
        Collection<CategoryEntity> categories = getCategories(dto.getCategories());
        BookEntity entity = new BookEntity();
        entity.getCategories().addAll(categories);
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setGenre(dto.getGenre());
        entity.setAnnotation(dto.getAnnotation());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setCreatedAt(Timestamp.from(CommonUtil.SYSTEM_CLOCK.instant()));
        entity.setIsAvailable(true);
        entity.setLanguage(dto.getLanguage());
        entityManager.persist(entity);
        return entityManager.merge(entity);
    }

    @Override
    public BookEntity find(int bookId) {
        return entityManager.find(BookEntity.class, bookId);
    }

    private Collection<CategoryEntity> getCategories(int[] categoryIds) {
        return Optional.ofNullable(categoryIds)
                .stream()
                .flatMapToInt(Arrays::stream)
                .mapToObj(id -> entityManager.find(CategoryEntity.class, id))
                .collect(Collectors.toList());
    }
}
