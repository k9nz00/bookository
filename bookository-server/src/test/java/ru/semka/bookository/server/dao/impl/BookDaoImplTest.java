package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.util.ComponentCommonUtil;

import java.time.Clock;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BookDaoImplTest {
    private final int defaultLimit = 15;
    private final EntityManager entityManager = mock(EntityManager.class);
    private final ComponentCommonUtil commonUtil = mock(ComponentCommonUtil.class);
    private final BookDaoImpl bookDao = new BookDaoImpl(entityManager, commonUtil, defaultLimit);

    @BeforeEach
    void setUp() {
        Clock clock = Clock.systemUTC();
        when(commonUtil.getSystemClock()).thenReturn(clock);
    }

    @Test
    void save() {
        bookDao.save(getRequestDto());
        verify(entityManager).merge(argThat(entityToSave -> {
            BookEntity book = (BookEntity) entityToSave;

            assertEquals("name", book.getName());
            assertEquals("author", book.getAuthor());
            assertEquals("genre", book.getGenre());
            assertEquals("annotation", book.getAnnotation());
            assertEquals(Language.RU, book.getLanguage());
            assertEquals(3, book.getCategories().size());

            return true;
        }));

    }

    @Test
    void update() {
        int bookId = 1;
        BookEntity entity = mock(BookEntity.class);
        when(entityManager.find(eq(BookEntity.class), anyInt())).thenReturn(entity);
        when(entityManager.merge(entity)).thenReturn(entity);

        bookDao.update(bookId, getRequestDto());

        verify(entity).setName("name");
        verify(entity).setAuthor("author");
        verify(entity).setGenre("genre");
        verify(entity).setAnnotation("annotation");
        verify(entity).setLanguage(Language.RU);
        verify(entity).setUpdatedAt(any());

        verify(entity).setCategories(argThat(categoryEntities -> {
            assertEquals(3, categoryEntities.size());
            return true;
        }));
    }

    @Test
    void updateWithNullableDto() {
        int bookId = 1;
        BookEntity entity = mock(BookEntity.class);
        when(entityManager.find(eq(BookEntity.class), anyInt())).thenReturn(entity);
        when(entityManager.merge(entity)).thenReturn(entity);

        bookDao.update(bookId, mock(BookRequestDto.class));

        verify(entity, never()).setName(anyString());
        verify(entity, never()).setAuthor(anyString());
        verify(entity, never()).setGenre(anyString());
        verify(entity, never()).setAnnotation(anyString());
        verify(entity, never()).setLanguage(any());
        verify(entity).setUpdatedAt(any());
        verify(entity, never()).setCategories(any());
    }

    @Test
    void updateNotFound() {
        int bookId = 1;
        when(entityManager.find(any(), anyInt())).thenReturn(null);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> bookDao.update(bookId, getRequestDto())
        );
        assertEquals("Не найдена карточка книги с id = 1", exception.getMessage());
    }

    @Test
    void getBooks() {
        BookCriteriaDto criteriaDto = mock(BookCriteriaDto.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        TypedQuery typedQuery = mock(TypedQuery.class);

        when(criteriaDto.getLimit()).thenReturn(10);
        when(criteriaBuilder.createQuery(BookEntity.class)).thenReturn(criteriaQuery);
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);

        Collection<BookEntity> books = bookDao.getBooks(criteriaDto, null);

        verify(typedQuery).setMaxResults(10);
        verify(typedQuery).setFirstResult(0);
        assertTrue(books.isEmpty());
    }

    @Test
    void getDetails() {
        int bookId = 1;
        BookDetailsEntity entity = mock(BookDetailsEntity.class);
        when(entityManager.find(any(), eq(bookId))).thenReturn(entity);

        Optional<BookDetailsEntity> details = bookDao.getDetails(bookId);
        assertTrue(details.isPresent());
        verify(entityManager, times(1)).find(BookDetailsEntity.class, bookId);
        assertEquals(entity, details.get());
    }

    @Test
    void deleteBook() {
        int bookId = 1;
        String queryString = "DELETE from BookEntity be where id = :id";
        Query query = mock(Query.class);
        when(entityManager.createQuery(anyString())).thenReturn(query);

        bookDao.deleteBook(bookId);

        verify(query).setParameter("id", bookId);
        verify(query, times(1)).executeUpdate();
        verify(entityManager).createQuery((String) argThat(queryParam -> {
            assertEquals(queryParam, queryString);
            return true;
        }));
    }

    private BookRequestDto getRequestDto() {
        BookRequestDto dto = mock(BookRequestDto.class);
        when(dto.getName()).thenReturn("name");
        when(dto.getAuthor()).thenReturn("author");
        when(dto.getGenre()).thenReturn("genre");
        when(dto.getAnnotation()).thenReturn("annotation");
        when(dto.getLanguage()).thenReturn(Language.RU);
        when(dto.getLanguage()).thenReturn(Language.RU);
        when(dto.getCategories()).thenReturn(Set.of(1, 2, 3));

        return dto;
    }
}
