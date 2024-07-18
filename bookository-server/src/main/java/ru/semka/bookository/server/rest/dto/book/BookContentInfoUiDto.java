package ru.semka.bookository.server.rest.dto.book;

import ru.semka.bookository.server.common.enums.BookFormat;

public record BookContentInfoUiDto(
        Integer id,
        Integer bookId,
        String size,
        BookFormat format
) {
}
