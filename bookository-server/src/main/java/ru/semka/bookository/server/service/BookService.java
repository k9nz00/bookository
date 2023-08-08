package ru.semka.bookository.server.service;

import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.CreateBookRequestDto;

import java.io.IOException;

public interface BookService {
    void save(CreateBookRequestDto dto, MultipartFile book, MultipartFile bookCover) throws IOException;

    BookDetailsUiDto getDetails(int bookId);

    String getBookContent(int bookId, int bookContentId);
}
