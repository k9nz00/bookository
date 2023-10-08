package ru.semka.bookository.server.rest.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.semka.bookository.server.common.enums.BookFormat;

@Data
@AllArgsConstructor
public class BookContentInfoUiDto {
    private final Integer id;
    private final Integer bookId;
    private final String size;
    private final BookFormat format;
}
