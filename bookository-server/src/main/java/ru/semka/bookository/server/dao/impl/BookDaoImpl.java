package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.dao.AbstractDao;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.dto.SearchCriteriaDto;
import ru.semka.bookository.server.dao.entity.*;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.util.CommonUtil;
import ru.semka.bookository.server.util.DaoUtil;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookDaoImpl extends AbstractDao implements BookDao {

    public BookDaoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public BookEntity save(BookRequestDto dto) {
        Collection<CategoryEntity> categories = getCategories(dto.getCategories());
        BookEntity entity = new BookEntity();
        entity.getCategories().addAll(categories);
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setGenre(dto.getGenre());
        entity.setAnnotation(dto.getAnnotation());
        entity.setName(dto.getName());
        entity.setCreatedAt(Timestamp.from(CommonUtil.SYSTEM_CLOCK.instant()));
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
        bookEntity.setUpdatedAt(Timestamp.from(CommonUtil.SYSTEM_CLOCK.instant()));
        entityManager.merge(bookEntity);
        return bookEntity;
    }

    @Override
    public Collection<BookEntity> getBooks(BookCriteriaDto criteriaDto) {
        SearchCriteriaDto<BookEntity> searchCriteria = DaoUtil.createCriteria(criteriaDto, BookEntity.class);
        return execute(searchCriteria);
    }

    @Override
    public BookDetailsEntity find(int bookId) {
        return entityManager.find(BookDetailsEntity.class, bookId);
    }

    @Override
    public void saveBookContent(int bookId, MultipartFile book, BookFormat bookFormat) throws IOException {
        BookContentEntity entity = new BookContentEntity();
        entity.setBookId(bookId);
        entity.setSize(book.getSize());
        entity.setBookFormat(bookFormat);
        entity.setContent(book.getBytes());
        entityManager.persist(entity);
        entityManager.merge(entity);
    }

    @Override
    public void deleteBookContent(int bookId, int bookContentId) {
        Query deleteQuery = entityManager.createNativeQuery(
                "DELETE FROM bookository.book_content WHERE id = :id AND book_id = :book_id");
        deleteQuery.setParameter("id", bookContentId);
        deleteQuery.setParameter("book_id", bookId);
        deleteQuery.executeUpdate();
    }

    @Override
    public Collection<BookContentInfoEntity> getContentInfo(int bookId) {
        Query query = entityManager.createNativeQuery(
                "SELECT id, book_id, size, format FROM bookository.book_content WHERE book_id = :book_id",
                BookContentInfoEntity.class
        );
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }

    @Override
    public byte[] getBookContent(int bookId, int bookContentId) {
        Query query = entityManager.createNativeQuery(
                "SELECT content FROM bookository.book_content WHERE id = :id AND book_id = :book_id",
                byte[].class
        );
        query.setParameter("book_id", bookId);
        query.setParameter("id", bookContentId);
        return (byte[]) query.getSingleResult();
    }

    private Collection<CategoryEntity> getCategories(int[] categoryIds) {
        return Optional.ofNullable(categoryIds)
                .stream()
                .flatMapToInt(Arrays::stream)
                .mapToObj(id -> entityManager.find(CategoryEntity.class, id))
                .collect(Collectors.toList());
    }
}
