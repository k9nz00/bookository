package ru.semka.bookository.migration.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.semka.bookository.migration.validation.impl.MigrationProfileValidatorImpl;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MigrationProfileValidatorImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MigrationProfileValidator {
    String message() default "Invalid profile name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
