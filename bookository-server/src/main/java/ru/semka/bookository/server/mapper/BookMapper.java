package ru.semka.bookository.server.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.util.MapperUtil;

@Mapper(
        componentModel = "spring",
        uses = {MapperUtil.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BookMapper {

    BookUiDto bookEntityToBookUiDto(BookEntity entity);

    @Mapping(source = "entity.bookContentsInfo", target = "bookContentInfo", qualifiedByName = "getContentInfo")
    BookDetailsUiDto bookDetailsEntityToBookDetailsDto(BookDetailsEntity entity);
}