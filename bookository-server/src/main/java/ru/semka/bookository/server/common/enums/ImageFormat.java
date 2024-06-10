package ru.semka.bookository.server.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ImageFormat {
    PNG("png"),
    JPEG("jpeg"),
    JPG("jpg"),
    GIF("gif");

    private final String value;

    public static ImageFormat fromValue(String input) {
        for (ImageFormat value : ImageFormat.values()) {
            if (value.getValue().equals(input)) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.format(
                "Invalid image format value: %s. Available values: %s",
                input,
                Arrays.toString(Arrays.stream(ImageFormat.values()).map(ImageFormat::name).toArray())
        ));

    }
}
