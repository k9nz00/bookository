package ru.semka.bookository.server.validator.impl;

import jakarta.validation.ConstraintValidatorContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookSortColumnValidatorImplTest {

    private final BookSortColumnValidatorImpl validator = new BookSortColumnValidatorImpl();

    @ParameterizedTest
    @MethodSource("getSortColumns")
    void isValid(String sortColumn) {
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        assertTrue(validator.isValid(sortColumn, context));
    }

    @Test
    void isNotValid() {
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        assertFalse(validator.isValid("notValidVale", context));
    }

    @Test
    void isValidCHeckAvailableColumns() {
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        Collection<String> availableColumns = validator.getAvailableColumns();
        Assertions.assertThat(availableColumns)
                .containsExactlyInAnyOrder("id", "name", "genre", "author", "language", "createdAt");
    }

    private static Stream<Arguments> getSortColumns() {
        return Stream.of(
                Arguments.of("id"),
                Arguments.of("name"),
                Arguments.of("genre"),
                Arguments.of("author"),
                Arguments.of("author"),
                Arguments.of("author"),
                Arguments.of((Object) null)
        );
    }
}