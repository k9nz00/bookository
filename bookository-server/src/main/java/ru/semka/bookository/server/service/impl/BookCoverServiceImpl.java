package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.dao.BookCoverDao;
import ru.semka.bookository.server.dao.entity.BookCoverEntity;
import ru.semka.bookository.server.service.BookCoverService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BookCoverServiceImpl implements BookCoverService {
    private final BookCoverDao bookCoverDao;

    @Override
    public void saveCover(int bookId, MultipartFile cover) throws IOException {
        BookCoverEntity entity = new BookCoverEntity();
        entity.setId(bookId);
        entity.setSize(cover.getSize());
        entity.setPreview(cover.getBytes());

        bookCoverDao.save(entity);
    }

    @Override
    public void deleteCover(int bookId) {
        bookCoverDao.deleteById(bookId);
    }
}
