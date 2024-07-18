package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.ImageFormat;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.BookCoverDao;
import ru.semka.bookository.server.dao.entity.BookCoverEntity;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.util.FileUtil;
import ru.semka.bookository.server.util.MapperUtil;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BookCoverServiceImpl implements BookCoverService {
    private final BookCoverDao bookCoverDao;

    @Override
    public void save(int bookId, MultipartFile cover) throws IOException {
        if (bookCoverDao.existsById(bookId)) {
            bookCoverDao.deleteById(bookId);
        }

        String fileFormat = FileUtil.getFileFormat(cover);
        ImageFormat format = ImageFormat.fromValue(fileFormat);

        BookCoverEntity entity = new BookCoverEntity();
        entity.setId(bookId);
        entity.setSize(cover.getSize());
        entity.setData(cover.getBytes());
        entity.setFormat(format.getValue());

        bookCoverDao.save(entity);
    }

    @Override
    public String get(int bookId) {
        return bookCoverDao.findById(bookId)
                .map(entity -> MapperUtil.getBase64EncodedImage(entity.getData(), entity.getFormat()))
                .orElseThrow(() -> new ResourceNotFoundException("Не найдена обложка с id = %d".formatted(bookId)));

    }

    @Override
    public void delete(int bookId) {
        if (!bookCoverDao.existsById(bookId)) {
            throw new ResourceNotFoundException("Не найдена обложка с id = %d".formatted(bookId));
        }
        bookCoverDao.deleteById(bookId);
    }

    @Override
    public Boolean isExists(int bookId) {
        return bookCoverDao.existsById(bookId);
    }
}
