package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.BookContentDao;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.entity.BookContentEntity;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.service.BookContentService;
import ru.semka.bookository.server.util.FileUtil;
import ru.semka.bookository.server.util.ResponseUtil;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookContentServiceImpl implements BookContentService {
    private final BookContentDao bookContentDao;
    private final BookDao bookDao;

    @Override
    public int save(int bookId, MultipartFile book) throws IOException {
        BookContentEntity entity = new BookContentEntity();
        entity.setBookId(bookId);
        entity.setName(book.getName());
        entity.setSize(book.getSize());
        entity.setBookFormat(getFormat(book));
        entity.setContent(book.getBytes());

        bookContentDao.save(entity);
        return entity.getId();
    }

    @Override
    public ResponseEntity<Resource> get(int bookId, int bookContentId) {
        Optional<BookContentEntity> byIdAndAndBookId = bookContentDao.findByIdAndAndBookId(bookContentId, bookId);
        return byIdAndAndBookId
                .map(entity -> {
                    Optional<BookDetailsEntity> details = bookDao.getDetails(bookId);
                    String bookName = details.map(book -> "%s.%s".formatted(book.getName(), entity.getBookFormat().name().toLowerCase()))
                            .orElseGet(() -> "%s.%s".formatted(entity.getName(), entity.getBookFormat().name().toLowerCase()));
                    Resource resource = new ByteArrayResource(entity.getContent());

                    return ResponseEntity.ok()
                            .headers(ResponseUtil.getHeaders(bookName))
                            .body(resource);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found book content for book with id = %d and content id = %d".formatted(bookId, bookContentId)
                ));
    }

    @Override
    public void delete(int bookId, int bookContentId) {
        if (bookDao.getDetails(bookId).isEmpty() || !bookContentDao.existsById(bookContentId)) {
            throw new ResourceNotFoundException("Книжный файл с id = %d не найден".formatted(bookContentId));
        }
        bookContentDao.deleteById(bookContentId);
    }

    private BookFormat getFormat(final MultipartFile book) {
        String fileFormat = FileUtil.getFileFormat(book);
        return BookFormat.fromName(fileFormat.toUpperCase());
    }
}
