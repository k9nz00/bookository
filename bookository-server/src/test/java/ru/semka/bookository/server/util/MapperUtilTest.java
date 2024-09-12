package ru.semka.bookository.server.util;

import org.junit.jupiter.api.Test;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.dao.entity.BookContentEntity;
import ru.semka.bookository.server.rest.dto.book.BookContentInfoUiDto;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MapperUtilTest {
    @Test
    void getBase64EncodedImage() {
        String sourseToEncode = "Base64 encoded String";
        String base64EncodedString = MapperUtil.getBase64EncodedImage(sourseToEncode.getBytes(), "png");
        assertEquals("data:image/png;base64,QmFzZTY0IGVuY29kZWQgU3RyaW5n", base64EncodedString);
    }

    @Test
    void getBase64EncodedImageWithNullValue() {
        String base64EncodedString = MapperUtil.getBase64EncodedImage(null, "png");
        assertNull(base64EncodedString);
    }

    @Test
    void getBase64EncodedImageWithEmptyValue() {
        String base64EncodedString = MapperUtil.getBase64EncodedImage(new byte[]{}, "png");
        assertNull(base64EncodedString);
    }

    @Test
    void getContentInfo() {
        List<BookContentInfoUiDto> contentInfo = (List<BookContentInfoUiDto>) MapperUtil.getContentInfo(getContent());
        assertFalse(contentInfo.isEmpty());
        assertEquals(2, contentInfo.size());

        BookContentInfoUiDto uiDto1 = contentInfo.get(0);
        assertEquals(1, uiDto1.id());
        assertEquals(2, uiDto1.bookId());
        assertEquals(BookFormat.EPUB, uiDto1.format());
        assertEquals("1 KB", uiDto1.size());

        BookContentInfoUiDto uiDto2 = contentInfo.get(1);
        assertEquals(10, uiDto2.id());
        assertEquals(20, uiDto2.bookId());
        assertEquals(BookFormat.PDF, uiDto2.format());
        assertEquals("1 MB", uiDto2.size());
    }

    private Collection<BookContentEntity> getContent() {
        BookContentEntity uiDto1 = mock(BookContentEntity.class);
        when(uiDto1.getId()).thenReturn(1);
        when(uiDto1.getBookId()).thenReturn(2);
        when(uiDto1.getName()).thenReturn("testBookName1");
        when(uiDto1.getSize()).thenReturn(1024L);
        when(uiDto1.getBookFormat()).thenReturn(BookFormat.EPUB);
        when(uiDto1.getContent()).thenReturn(new byte[]{1, 2, 3});

        BookContentEntity uiDto2 = mock(BookContentEntity.class);
        when(uiDto2.getId()).thenReturn(10);
        when(uiDto2.getBookId()).thenReturn(20);
        when(uiDto2.getName()).thenReturn("testBookName2");
        when(uiDto2.getSize()).thenReturn(2048000L);
        when(uiDto2.getBookFormat()).thenReturn(BookFormat.PDF);
        when(uiDto2.getContent()).thenReturn(new byte[]{4, 5, 6,});

        return List.of(uiDto1, uiDto2);
    }
}