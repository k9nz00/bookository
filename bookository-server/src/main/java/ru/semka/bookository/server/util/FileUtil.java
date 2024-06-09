package ru.semka.bookository.server.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {
    public static String getFileFormat(final MultipartFile file) {
        String filename = file.getResource().getFilename();
        if (Objects.nonNull(filename) && filename.contains(".")) {
            int indexFormat = filename.lastIndexOf(".");
            return filename.substring(indexFormat + 1);
        } else {
            throw new IllegalArgumentException("File not contain the type of file extension");
        }
    }
}
