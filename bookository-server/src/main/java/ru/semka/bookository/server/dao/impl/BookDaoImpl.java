package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.semka.bookository.server.dao.AbstractDao;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.dto.SearchCriteriaDto;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.util.ComponentCommonUtil;
import ru.semka.bookository.server.util.DaoUtil;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class BookDaoImpl extends AbstractDao implements BookDao {

    private final ComponentCommonUtil commonUtil;

    public BookDaoImpl(EntityManager entityManager,
                       ComponentCommonUtil commonUtil,
                       final @Value("${app-config.pagination.default-limit}") int defaultLimit) {
        super(entityManager, defaultLimit);
        this.commonUtil = commonUtil;
    }

    @Override
    public BookEntity save(BookRequestDto dto) {
        Collection<CategoryEntity> categories = getCategories(dto.getCategories());
        BookEntity entity = new BookEntity();
        entity.setCategories(categories);
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setGenre(dto.getGenre());
        entity.setAnnotation(dto.getAnnotation());
        entity.setName(dto.getName());
        entity.setCreatedAt(Timestamp.from(commonUtil.getSystemClock().instant()));
        entity.setIsAvailable(true);
        entity.setLanguage(dto.getLanguage());
        entityManager.persist(entity);
        return entityManager.merge(entity);
    }

    @Override
    public BookEntity update(int bookId, BookRequestDto dto) {
        BookEntity bookEntity = entityManager.find(BookEntity.class, bookId);
        if (Objects.nonNull(dto.getName())) {
            bookEntity.setName(dto.getName());
        }
        if (Objects.nonNull(dto.getAuthor())) {
            bookEntity.setAuthor(dto.getAuthor());
        }
        if (Objects.nonNull(dto.getGenre())) {
            bookEntity.setGenre(dto.getGenre());
        }
        if (Objects.nonNull(dto.getLanguage())) {
            bookEntity.setLanguage(dto.getLanguage());
        }
        if (Objects.nonNull(dto.getAnnotation())) {
            bookEntity.setAnnotation(dto.getAnnotation());
        }
        if (Objects.nonNull(dto.getCategories())) {
            bookEntity.setCategories(getCategories(dto.getCategories()));
        }
        bookEntity.setUpdatedAt(Timestamp.from(commonUtil.getSystemClock().instant()));
        entityManager.merge(bookEntity);
        return bookEntity;
    }

    @Override
    public Collection<BookEntity> getBooks(BookCriteriaDto criteriaDto,
                                           PredicateProvider<BookEntity> predicateProvider) {
        SearchCriteriaDto<BookEntity> searchCriteria = DaoUtil.createCriteria(
                criteriaDto,
                predicateProvider,
                BookEntity.class
        );
        return execute(searchCriteria);
    }

    @Override
    public Optional<BookDetailsEntity> getDetails(int bookId) {
        return Optional.ofNullable(entityManager.find(BookDetailsEntity.class, bookId));
    }

    @Override
    public void deleteBook(int bookId) {
        Query query = entityManager.createQuery("DELETE from BookEntity be where id = :id");
        query.setParameter("id", bookId);
        query.executeUpdate();
    }

    private Collection<CategoryEntity> getCategories(Collection<Integer> categoryIds) {
        return Optional.ofNullable(categoryIds).stream()
                .flatMap(Collection::stream)
                .map(id -> entityManager.find(CategoryEntity.class, id))
                .collect(Collectors.toList());
    }
}
