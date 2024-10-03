package ru.semka.bookository.server.mapper;

import org.junit.jupiter.api.Test;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.dao.entity.*;
import ru.semka.bookository.server.rest.dto.book.BookCsvDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookMapperTest {
    private final BookMapper mapper = new BookMapperImpl();

    @Test
    void bookEntityToBookUiDto() {
        BookEntity entity = getEntity(true);
        BookUiDto uiDto = mapper.bookEntityToBookUiDto(entity);

        assertEquals(entity.getId(), uiDto.id());
        assertEquals(entity.getName(), uiDto.name());
        assertEquals(entity.getGenre(), uiDto.genre());
        assertEquals(entity.getAnnotation(), uiDto.annotation());
        assertEquals(entity.getIsAvailable(), uiDto.isAvailable());
        assertEquals(entity.getLanguage(), uiDto.language());
        assertEquals(entity.getCreatedAt(), uiDto.createdAt());
        assertEquals(entity.getUpdatedAt(), uiDto.updatedAt());

        //todo Сделать более корректную проверку
        assertEquals(entity.getAuthors().size(), uiDto.authors().size());
        assertEquals(entity.getCategories().size(), uiDto.categories().size());
    }

    @Test
    void bookEntityToBookUiDtoWithNull() {
        BookUiDto uiDto = mapper.bookEntityToBookUiDto(null);
        assertNull(uiDto);
    }

    @Test
    void bookEntityToBookUiDtoWithoutCategories() {
        BookEntity entity = getEntity(false);
        BookUiDto uiDto = mapper.bookEntityToBookUiDto(entity);

        assertNull(uiDto.categories());
    }

    @Test
    void bookDetailsEntityToBookDetailsDto() {
        BookDetailsEntity bookDetailsEntity = getBookDetailsEntity(true);
        BookDetailsUiDto detailsUiDto = mapper.bookDetailsEntityToBookDetailsDto(bookDetailsEntity);

        assertEquals(bookDetailsEntity.getId(), detailsUiDto.id());
        assertEquals(bookDetailsEntity.getName(), detailsUiDto.name());

        assertEquals(bookDetailsEntity.getGenre(), detailsUiDto.genre());
        assertEquals(bookDetailsEntity.getAnnotation(), detailsUiDto.annotation());
        assertEquals(bookDetailsEntity.getLanguage(), detailsUiDto.language());
        assertEquals(bookDetailsEntity.getIsAvailable(), detailsUiDto.isAvailable());
        assertEquals(bookDetailsEntity.getCreatedAt(), detailsUiDto.createdAt());
        assertEquals(bookDetailsEntity.getUpdatedAt(), detailsUiDto.updatedAt());

        Collection<CategoryEntity> categoriesFromEntity = bookDetailsEntity.getCategories();
        Collection<CategoryUiDto> categoriesFromUiDto = detailsUiDto.categories();
        assertEquals(categoriesFromEntity.size(), categoriesFromUiDto.size());
        CategoryEntity categoryEntity = categoriesFromEntity.stream().findFirst().get();
        CategoryUiDto uiDto = categoriesFromUiDto.stream().findFirst().get();
        assertEquals(categoryEntity.getId(), uiDto.id());
        assertEquals(categoryEntity.getName(), uiDto.name());

        //todo Сделать более корректную проверку
        assertEquals(bookDetailsEntity.getAuthors().size(), detailsUiDto.authors().size());
    }

    @Test
    void bookDetailsEntityToBookDetailsDtoWithNull() {
        BookDetailsUiDto detailsUiDto = mapper.bookDetailsEntityToBookDetailsDto(null);
        assertNull(detailsUiDto);
    }

    @Test
    void bookDetailsEntityToBookDetailsDtoWithoutCategories() {
        BookDetailsEntity bookDetailsEntity = getBookDetailsEntity(false);
        BookDetailsUiDto detailsUiDto = mapper.bookDetailsEntityToBookDetailsDto(bookDetailsEntity);

        assertNull(detailsUiDto.categories());
    }

    @Test
    void csvDtoToBookRequestDto() {
        BookCsvDto bookCsvDto = getBookCsvDto(true);
        BookRequestDto bookRequestDto = mapper.csvDtoToBookRequestDto(bookCsvDto);

        assertEquals(bookCsvDto.getName(), bookRequestDto.getName());
        assertEquals(bookCsvDto.getGenre(), bookRequestDto.getGenre());
        assertEquals(bookCsvDto.getLanguage(), bookRequestDto.getLanguage());
        assertEquals(bookCsvDto.getAnnotation(), bookRequestDto.getAnnotation());
        assertEquals(bookCsvDto.getCategories(), bookRequestDto.getCategories());
    }

    @Test
    void csvDtoToBookRequestDtoWithOutCategories() {
        BookCsvDto bookCsvDto = getBookCsvDto(false);
        BookRequestDto bookRequestDto = mapper.csvDtoToBookRequestDto(bookCsvDto);

        assertNull(bookRequestDto.getCategories());
    }

    @Test
    void csvDtoToBookRequestDtoWithNull() {
        BookRequestDto bookRequestDto = mapper.csvDtoToBookRequestDto(null);
        assertNull(bookRequestDto);
    }

    private BookEntity getEntity(boolean withCategories) {
        String createTimeStamp = "2024-09-20 23:16:25";
        String updateTimeStamp = "2024-09-21 23:16:25";
        AuthorEntity authorEntity = new AuthorEntity(
                1,
                "Александр",
                "Сергеевич",
                "Пушкин"
        );

        BookEntity entity = new BookEntity();
        entity.setId(1);
        entity.setName("name");
        entity.setAuthors(Collections.singleton(authorEntity));
        entity.setGenre("genre");
        entity.setAnnotation("annotation");
        entity.setIsAvailable(true);
        entity.setLanguage(Language.RU);
        entity.setCreatedAt(Timestamp.valueOf(createTimeStamp));
        entity.setUpdatedAt(Timestamp.valueOf(updateTimeStamp));
        if (withCategories) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(2);
            categoryEntity.setName("categoryName");

            entity.setCategories(List.of(categoryEntity));
        } else {
            entity.setCategories(null);
        }
        return entity;
    }

    private BookDetailsEntity getBookDetailsEntity(boolean withCategories) {
        String createTimeStamp = "2024-09-20 23:16:25";
        String updateTimeStamp = "2024-09-21 23:16:25";

        AuthorEntity authorEntity = new AuthorEntity(
                1,
                "Александр",
                "Сергеевич",
                "Пушкин"
        );

        BookContentEntity bookContentEntity = new BookContentEntity();
        bookContentEntity.setId(10);
        bookContentEntity.setBookId(1);
        bookContentEntity.setName("bookContentName");
        bookContentEntity.setSize(100L);
        bookContentEntity.setBookFormat(BookFormat.TXT);
        bookContentEntity.setContent("content".getBytes());

        BookDetailsEntity detailsEntity = new BookDetailsEntity();
        detailsEntity.setId(1);
        detailsEntity.setName("name");
        detailsEntity.setAuthors(Collections.singleton(authorEntity));
        detailsEntity.setGenre("genre");
        detailsEntity.setAnnotation("annotation");
        detailsEntity.setIsAvailable(true);
        detailsEntity.setLanguage(Language.RU);
        detailsEntity.setCreatedAt(Timestamp.valueOf(createTimeStamp));
        detailsEntity.setUpdatedAt(Timestamp.valueOf(updateTimeStamp));
        if (withCategories) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(2);
            categoryEntity.setName("categoryName");
            detailsEntity.setCategories(List.of(categoryEntity));
        } else {
            detailsEntity.setCategories(null);
        }

        detailsEntity.setBookContentsInfo(List.of(bookContentEntity));

        return detailsEntity;
    }

    private BookCsvDto getBookCsvDto(boolean withCategories) {
        BookCsvDto csvDto = new BookCsvDto();
        csvDto.setName("name");
        csvDto.setGenre("genre");
        csvDto.setLanguage(Language.RU);
        csvDto.setAnnotation("annotation");
        if (withCategories) {
            csvDto.setCategories(Set.of(1, 2, 3));
        }

        return csvDto;
    }
}