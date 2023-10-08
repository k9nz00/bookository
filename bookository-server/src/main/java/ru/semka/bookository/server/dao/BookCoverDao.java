package ru.semka.bookository.server.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookCoverDao {
    @Transactional
    void saveBigCover(int bookId, MultipartFile bookCover) throws IOException;

    @Transactional
    void saveSmallCover(int bookId, long size, byte[] data);

    @Transactional
    void deleteBigCover(int bookId);

    @Transactional
    void deleteSmallCover(int bookId);
}
