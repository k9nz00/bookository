package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.entity.*;
import ru.semka.bookository.server.rest.dto.book.CreateBookRequestDto;
import ru.semka.bookository.server.util.CommonUtil;

import java.io.IOException;
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
