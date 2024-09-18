package ru.semka.bookository.server.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.mapstruct.Named;
import ru.semka.bookository.server.dao.entity.BookContentEntity;
import ru.semka.bookository.server.rest.dto.book.BookContentInfoUiDto;

import java.util.Base64;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperUtil {
    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    @Named("getBase64EncodedImage")
    public static String getBase64EncodedImage(byte[] byteContent, String fileFormat) {
        if (Objects.isNull(byteContent) || byteContent.length == 0) {
            return null;
        } else {
            return "data:image/%s;base64,".formatted(fileFormat) + ENCODER.encodeToString(byteContent);
        }
    }

    @Named("getContentInfo")
    public static Collection<BookContentInfoUiDto> getContentInfo(Collection<BookContentEntity> infoEntities) {
        return infoEntities.stream()
                .map(entity -> new BookContentInfoUiDto(
                        entity.getId(),
                        entity.getBookId(),
                        FileUtils.byteCountToDisplaySize(entity.getSize()),
                        entity.getBookFormat()
                ))
                .toList();
    }

}
