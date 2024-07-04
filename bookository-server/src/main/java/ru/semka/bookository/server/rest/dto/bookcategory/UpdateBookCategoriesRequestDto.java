package ru.semka.bookository.server.rest.dto.bookcategory;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class UpdateBookCategoriesRequestDto {

    @NotBlank
    @Length(min = 5)
    @Parameter(required = true)
    private String name;
}