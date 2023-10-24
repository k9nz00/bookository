package ru.semka.bookository.server.transformers.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.semka.bookository.server.dao.entity.BookContentEntity;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;

import java.util.Collection;

@AllArgsConstructor
@Getter
public class BookDetailsWrapper {
    private final BookDetailsEntity book;
    private final Collection<BookContentEntity> bookContentInfoEntities;
}
