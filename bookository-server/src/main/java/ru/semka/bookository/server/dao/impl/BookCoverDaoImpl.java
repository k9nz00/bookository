package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.dao.BookCoverDao;
import ru.semka.bookository.server.dao.entity.BookBigPreviewEntity;
import ru.semka.bookository.server.dao.entity.BookSmallPreviewEntity;

import java.io.IOException;

@Repository
@RequiredArgsConstructor
public class BookCoverDaoImpl implements BookCoverDao {
    private final EntityManager entityManager;

    @Override
    public void saveBigCover(int bookId, MultipartFile bookCover) throws IOException {
        BookBigPreviewEntity entity = new BookBigPreviewEntity();
        entity.setBookId(bookId);
        entity.setSize(bookCover.getSize());
        entity.setPreview(bookCover.getBytes());

        entityManager.persist(entity);
        entityManager.merge(entity);
    }

    @Override
    public void saveSmallCover(int bookId, long size, byte[] data) {

        BookSmallPreviewEntity entity = new BookSmallPreviewEntity();
        entity.setBookId(bookId);
        entity.setSize(size);
        entity.setPreview(data);
        entityManager.persist(entity);
        entityManager.merge(entity);

    }
}
