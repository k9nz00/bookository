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
    public BufferedImage resizeImage(byte[] imageData) throws IOException {
        BufferedImage imageFromBytes = createImageFromBytes(imageData);
        int height = imageFromBytes.getHeight() / 4;
        int width = imageFromBytes.getWidth() / 4;
        Image resultingImage = imageFromBytes.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    @Override
    public BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
