package ru.semka.bookository.server.service.impl;

import org.springframework.stereotype.Service;
import ru.semka.bookository.server.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public BufferedImage resizeImage(byte[] imageData) {
        BufferedImage imageFromBytes = createImageFromBytes(imageData);
        int height = imageFromBytes.getHeight() / 2;
        int width = imageFromBytes.getWidth() / 2;
        Image resultingImage = imageFromBytes.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    @Override
    public BufferedImage createImageFromBytes(byte[] imageData) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData)) {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
