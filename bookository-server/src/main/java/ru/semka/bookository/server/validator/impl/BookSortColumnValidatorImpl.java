package ru.semka.bookository.server.validator.impl;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.validator.AbstractStringValidator;
import ru.semka.bookository.server.validator.BookSortColumnValidator;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class BookSortColumnValidatorImpl
        extends AbstractStringValidator
        implements ConstraintValidator<BookSortColumnValidator, String> {

    private final Collection<String> availableColumns;

    public BookSortColumnValidatorImpl() {
        try {
            availableColumns = Set.of(BookCriteriaDto.class
                    .getDeclaredField("sortColumn")
                    .getAnnotation(Schema.class)
                    .allowableValues());
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(status)
                .map(value -> isValid(value, constraintValidatorContext, availableColumns))
                .orElse(true);
    }
}
