package ru.semka.bookository.server.rest.dto.bookcategory;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CreateBookCategoriesRequestDto {

    @NotBlank
    @Length(min = 5)
    private String name;
}