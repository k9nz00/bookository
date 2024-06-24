package ru.semka.bookository.server.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.semka.bookository.server.validator.impl.BookSortColumnValidatorImpl;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BookSortColumnValidatorImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BookSortColumnValidator {
    String message() default "Invalid sort column";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
