package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.semka.bookository.server.dao.AbstractDao;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.util.DaoUtil;

import java.util.Collection;
import java.util.Optional;

@Repository
@Slf4j
public class BookDaoImpl extends AbstractDao implements BookDao {

    public BookDaoImpl(EntityManager entityManager,
                       final @Value("${app-config.pagination.default-limit}") int defaultLimit) {
        super(entityManager, defaultLimit);
    }

    @Override
    public BookEntity save(BookEntity entity) {
        entityManager.persist(entity);
        return entityManager.merge(entity);
    }

    @Override
    public Collection<BookEntity> getBooks(BookCriteriaDto criteriaDto,
                                           PredicateProvider<BookEntity> predicateProvider) {
        return execute(DaoUtil.createCriteria(
                criteriaDto,
                predicateProvider,
                BookEntity.class
        ));
    }

    @Override
    public Optional<BookDetailsEntity> getDetails(int bookId) {
        return Optional.ofNullable(entityManager.find(BookDetailsEntity.class, bookId));
    }

    @Override
    public Optional<BookEntity> get(int bookId) {
        return Optional.ofNullable(entityManager.find(BookEntity.class, bookId));
    }

    @Override
    public void deleteBook(int bookId) {
        Query query = entityManager.createQuery("DELETE from BookEntity be where id = :id");
        query.setParameter("id", bookId);
        query.executeUpdate();
    }
}
