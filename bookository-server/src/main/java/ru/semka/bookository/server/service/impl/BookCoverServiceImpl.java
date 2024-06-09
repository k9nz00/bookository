package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.ImageFormat;
import ru.semka.bookository.server.dao.BookCoverDao;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.service.ImageService;
import ru.semka.bookository.server.util.FileUtil;

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
    public void saveCover(int bookId, MultipartFile cover) throws IOException {
        BufferedImage smallImage = imageService.resizeImage(cover.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageFormat imageFormat = ImageFormat.fromValue(FileUtil.getFileFormat(cover));
        ImageIO.write(smallImage, imageFormat.getValue(), baos);
        byte[] bytes = baos.toByteArray();
        bookCoverDao.saveBigCover(bookId, cover);
        bookCoverDao.saveSmallCover(bookId, bytes.length, bytes);
    }

    @Override
    public void deleteCover(int bookId) {
        bookCoverDao.deleteBigCover(bookId);
        bookCoverDao.deleteSmallCover(bookId);
    }
}
