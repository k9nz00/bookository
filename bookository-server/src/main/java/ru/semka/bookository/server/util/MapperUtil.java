package ru.semka.bookository.server.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;
import org.mapstruct.Named;
import ru.semka.bookository.server.dao.entity.BookContentInfoEntity;
import ru.semka.bookository.server.rest.dto.book.BookContentInfoUiDto;

import java.util.Base64;
import java.util.Collection;
import java.util.Objects;

@UtilityClass
public class MapperUtil {
    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    @Named("getBase64EncodedImage")
    public static String getBase64EncodedImage(byte[] byteContent) {
        if (Objects.isNull(byteContent) || byteContent.length == 0) {
            return null;
        } else {
            return "data:image/gpeg;base64," + ENCODER.encodeToString(byteContent);
        }
    }

    @Named("getContentInfo")
    public static Collection<BookContentInfoUiDto> getContentInfo(Collection<BookContentInfoEntity> infoEntities) {
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
