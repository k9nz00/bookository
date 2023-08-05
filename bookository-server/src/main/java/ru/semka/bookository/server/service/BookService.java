package ru.semka.bookository.server.service;

import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.BookUiDto;
import ru.semka.bookository.server.rest.dto.CreateBookRequestDto;

import java.io.IOException;

public interface BookService {
    void save(CreateBookRequestDto dto, MultipartFile bookCover) throws IOException;

    BookUiDto getDetails(int bookId);
}
