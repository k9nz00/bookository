package ru.semka.bookository.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookCoverService {
    void saveCover(int bookId, MultipartFile cover) throws IOException;

    void deleteCover(int bookId);
}
