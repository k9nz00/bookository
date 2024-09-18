package ru.semka.bookository.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface BookCoverService {
    void save(int bookId, MultipartFile cover);

    String get(int bookId);

    void delete(int bookId);

    Boolean isExists(int bookId);
}
