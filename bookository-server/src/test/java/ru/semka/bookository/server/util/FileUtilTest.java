package ru.semka.bookository.server.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileUtilTest {

    @ParameterizedTest
    @MethodSource("getFileFormats")
    void getFileFormat(String fileName, String formatValue) {
        Resource resource = mock(Resource.class);
        MultipartFile multipartFile = mock(MultipartFile.class);

        when(resource.getFilename()).thenReturn(fileName);
        when(multipartFile.getResource()).thenReturn(resource);

        String fileFormat = FileUtil.getFileFormat(multipartFile);
        assertEquals(formatValue, fileFormat);
    }

    @ParameterizedTest
    @MethodSource("getFileFormatsForError")
    void getFileFormat_FileWithOutFormat(String fileName) {
        Resource resource = mock(Resource.class);
        MultipartFile multipartFile = mock(MultipartFile.class);

        when(resource.getFilename()).thenReturn(fileName);
        when(multipartFile.getResource()).thenReturn(resource);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> FileUtil.getFileFormat(multipartFile)
        );

        assertEquals("File [%s] not contain the type of file extension".formatted(fileName), exception.getMessage());
    }

    private static Stream<Arguments> getFileFormats() {
        return Stream.of(
                Arguments.of("test.txt", "txt"),
                Arguments.of("test.pdf", "pdf"),
                Arguments.of("test.doc", "doc")
        );
    }

    private static Stream<Arguments> getFileFormatsForError() {
        return Stream.of(
                Arguments.of("test"),
                Arguments.of((Object) null)
        );
    }
}