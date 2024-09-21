package ru.semka.bookository.server.validator;

import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;

public abstract class AbstractStringValidator {
    public boolean isValid(final String value,
                           final ConstraintValidatorContext constraintValidatorContext,
                           final Collection<String> availableValues) {
        if (availableValues.contains(value)) {
            return true;
        } else {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(
                            String.format("Provided value [%s] is not valid. Allowable values = [%s]", value, String.join(", ", availableValues)))
                    .addConstraintViolation();
            return false;
        }
    }
}