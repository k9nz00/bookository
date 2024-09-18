package ru.semka.bookository.server.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.cover.CoverDto;
import ru.semka.bookository.server.service.BookCoverService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class BookCoverControllerTest {
    private final BookCoverService bookCoverService = mock(BookCoverService.class);
    private final BookCoverController controller = new BookCoverController(bookCoverService);

    @Test
    void saveBookCover() {
        int bookId = 1;
        MultipartFile multipartFile = mock(MultipartFile.class);
        doNothing().when(bookCoverService).save(anyInt(), any());

        controller.saveBookCover(bookId, multipartFile);

        verify(bookCoverService).save(bookId, multipartFile);
    }

    @Test
    void getBookCover() {
        int bookId = 1;
        when(bookCoverService.get(anyInt())).thenReturn("stringImageValue");

        CoverDto bookCover = controller.getBookCover(bookId);

        verify(bookCoverService).get(bookId);
        assertEquals("stringImageValue", bookCover.imageData());
    }

    @Test
    void deleteBookCover() {
        int bookId = 1;
        doNothing().when(bookCoverService).delete(anyInt());

        controller.deleteBookCover(bookId);

        verify(bookCoverService).delete(bookId);
    }
}