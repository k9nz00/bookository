package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.dao.BookCoverDao;
import ru.semka.bookository.server.dao.entity.BigBookPreviewEntity;
import ru.semka.bookository.server.dao.entity.SmallBookPreviewEntity;

import java.io.IOException;

@Repository
@RequiredArgsConstructor
public class BookCoverDaoImpl implements BookCoverDao {
    private final EntityManager entityManager;

    @Override
    public void saveBigCover(int bookId, MultipartFile bookCover) throws IOException {
        BigBookPreviewEntity entity = new BigBookPreviewEntity();
        entity.setId(bookId);
        entity.setSize(bookCover.getSize());
        entity.setPreview(bookCover.getBytes());

        entityManager.persist(entity);
        entityManager.merge(entity);
    }

    @Override
    public void saveSmallCover(int bookId, long size, byte[] data) {
        SmallBookPreviewEntity entity = new SmallBookPreviewEntity();
        entity.setId(bookId);
        entity.setSize(size);
        entity.setPreview(data);

        entityManager.persist(entity);
        entityManager.merge(entity);
    }

    @Override
    public void deleteBigCover(int bookId) {
        Query nativeQuery = entityManager.createNativeQuery("DELETE FROM bookository.big_book_preview WHERE id = :book_id");
        nativeQuery.setParameter("book_id", bookId);
        nativeQuery.executeUpdate();
    }

    @Override
    public void deleteSmallCover(int bookId) {
        Query nativeQuery = entityManager.createNativeQuery("DELETE FROM bookository.small_book_preview WHERE id = :book_id");
        nativeQuery.setParameter("book_id", bookId);
        nativeQuery.executeUpdate();
    }
}
