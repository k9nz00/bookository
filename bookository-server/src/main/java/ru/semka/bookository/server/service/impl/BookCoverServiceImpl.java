package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.dao.BookCoverDao;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BookCoverServiceImpl implements BookCoverService {
    private final BookCoverDao bookCoverDao;
    private final ImageService imageService;

    @Override
    public void saveCover(int bookId, MultipartFile bookCover) throws IOException {
        BufferedImage smallImage = imageService.resizeImage(bookCover.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(smallImage, "jpeg", baos);
        byte[] bytes = baos.toByteArray();
        bookCoverDao.saveBigCover(bookId, bookCover);
        bookCoverDao.saveSmallCover(bookId, bytes.length, bytes);
    }
}
