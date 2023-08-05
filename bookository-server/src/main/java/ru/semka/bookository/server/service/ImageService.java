package ru.semka.bookository.server.service;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageService {
    BufferedImage resizeImage(byte[] imageData) throws IOException;

    BufferedImage createImageFromBytes(byte[] imageData);
}
