package ru.semka.bookository.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookCoverService {
    void save(int bookId, MultipartFile cover) throws IOException;

    String get(int bookId);

    void delete(int bookId);

    Boolean isExists(int bookId);
}
