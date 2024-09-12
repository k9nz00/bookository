package ru.semka.bookository.server.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ResponseUtilTest {

    @ParameterizedTest
    @MethodSource("getFileNames")
    void getHeaders(String fileName, String encodedFileName) {
        HttpHeaders headers = ResponseUtil.getHeaders(fileName);

        assertFalse(headers.isEmpty());
        assertEquals(4, headers.size());
        List<String> RealFileNameFileNames = headers.get("Real-File-Name");
        assert RealFileNameFileNames != null;
        String RealFileNameFileName = RealFileNameFileNames.get(0);
        assertEquals(encodedFileName, RealFileNameFileName);
    }

    private static Stream<Arguments> getFileNames() {
        return Stream.of(
                Arguments.of("test.txt", "test.txt"),
                Arguments.of("тест.txt", "%D1%82%D0%B5%D1%81%D1%82.txt"),
                Arguments.of("te st.txt", "te_st.txt"),
                Arguments.of("те ст.txt", "%D1%82%D0%B5_%D1%81%D1%82.txt"),
                Arguments.of("te-st.txt", "te-st.txt"),
                Arguments.of("те-ст.txt", "%D1%82%D0%B5-%D1%81%D1%82.txt")
        );
    }
}