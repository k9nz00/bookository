package ru.semka.bookository.server.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.rest.dto.author.AuthorRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookControllerTest {
    private final BookService bookService = mock(BookService.class);
    private final BookController controller = new BookController(bookService);

    @Test
    void saveBook() {
        BookRequestDto requestDto = getRequestDto();
        BookUiDto uiDto = mock(BookUiDto.class);
        when(bookService.save(any())).thenReturn(uiDto);

        controller.saveBook(requestDto);

        verify(bookService).save(requestDto);
    }

    @Test
    void saveFromFile() {
        MultipartFile file = mock(MultipartFile.class);
        int booksSavedCount = 1;
        when(bookService.saveFromFile(any())).thenReturn(booksSavedCount);

        Integer saveFromFile = controller.saveFromFile(file);

        verify(bookService).saveFromFile(file);
        assertEquals(booksSavedCount, saveFromFile);
    }

    @Test
    void getBooks() {
        BookCriteriaDto criteriaDto = mock(BookCriteriaDto.class);
        Collection<BookUiDto> result = List.of(mock(BookUiDto.class));
        when(bookService.getList(any())).thenReturn(result);

        Collection<BookUiDto> books = controller.getBooks(criteriaDto);

        assertEquals(result, books);
        verify(bookService).getList(criteriaDto);
    }

    @Test
    void getBook() {
        int bookId = 1;
        BookDetailsUiDto detailsUiDto = mock(BookDetailsUiDto.class);
        when(bookService.getDetails(anyInt())).thenReturn(detailsUiDto);

        BookDetailsUiDto book = controller.getBook(bookId);

        assertEquals(detailsUiDto, book);
        verify(bookService).getDetails(bookId);
    }

    @Test
    void updateBook() {
        int bookId = 1;
        BookRequestDto requestDto = mock(BookRequestDto.class);
        BookUiDto result = mock(BookUiDto.class);
        when(bookService.update(anyInt(), any())).thenReturn(result);

        BookUiDto uiDto = controller.updateBook(bookId, requestDto);

        assertEquals(result, uiDto);
        verify(bookService).update(bookId, requestDto);
    }

    @Test
    void deleteBook() {
        int bookId = 1;
        doNothing().when(bookService).delete(anyInt());

        controller.deleteBook(bookId);

        verify(bookService).delete(bookId);
    }

    private BookRequestDto getRequestDto() {
        return new BookRequestDto(
                "name",
                List.of(new AuthorRequestDto("a", "b", "c")),
                "genre",
                Language.EN,
                "annotation",
                Set.of(1, 2, 3)
        );
    }
}