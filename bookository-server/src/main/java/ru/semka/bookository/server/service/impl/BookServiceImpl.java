package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.rest.dto.book.CreateBookRequestDto;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.service.BookService;
import ru.semka.bookository.server.transformers.Transformer;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookCoverService bookCoverService;
    private final Transformer<BookDetailsEntity, BookUiDto> bookDetailsTransformer;

    @Override
    public void save(CreateBookRequestDto dto, MultipartFile book, MultipartFile bookCover) throws IOException {
        BookEntity bookEntity = bookDao.save(dto);
        bookCoverService.saveCover(bookEntity.getId(), bookCover);
        bookDao.saveBookContent(bookEntity.getId(), book);
    }

    @Override
    public BookUiDto getDetails(int bookId) {
        BookDetailsEntity entity = bookDao.find(bookId);
        return bookDetailsTransformer.transform(entity);
    }
}
