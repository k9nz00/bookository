package ru.semka.bookository.server.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookContentService {
    int save(int bookId, MultipartFile book) throws IOException;

    ResponseEntity<Resource> get(int bookId, int bookContentId);

    void delete(int bookId, int bookContentId);
}
