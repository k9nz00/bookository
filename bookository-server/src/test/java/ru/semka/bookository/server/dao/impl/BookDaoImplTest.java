package ru.semka.bookository.server.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.junit.jupiter.api.Test;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.dao.entity.AuthorEntity;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BookDaoImplTest {
    private final int defaultLimit = 15;
    private final EntityManager entityManager = mock(EntityManager.class);
    private final BookDaoImpl bookDao = new BookDaoImpl(entityManager, defaultLimit);

    @Test
    void save() {
        bookDao.save(getRequestDto());

        verify(entityManager).merge(argThat(entityToSave -> {
            BookEntity book = (BookEntity) entityToSave;

            assertEquals("name", book.getName());
            assertEquals("genre", book.getGenre());
            assertEquals("annotation", book.getAnnotation());
            assertEquals(Language.RU, book.getLanguage());
            assertEquals(1, book.getCategories().size());

            return true;
        }));

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

    private BookEntity getRequestDto() {
        BookEntity dto = mock(BookEntity.class);
        when(dto.getName()).thenReturn("name");
        when(dto.getAuthors()).thenReturn(List.of(mock(AuthorEntity.class)));
        when(dto.getGenre()).thenReturn("genre");
        when(dto.getAnnotation()).thenReturn("annotation");
        when(dto.getLanguage()).thenReturn(Language.RU);
        when(dto.getLanguage()).thenReturn(Language.RU);
        when(dto.getCategories()).thenReturn(Set.of(mock(CategoryEntity.class)));

        return dto;
    }
}
