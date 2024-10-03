package ru.semka.bookository.server.rest.dto.author;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {

    @NotBlank
    @Length(min = 3)
    @Parameter(required = true)
    private String firstName;

    @NotBlank
    @Length(min = 3)
    @Parameter(required = true)
    private String surName;

    @NotBlank
    @Length(min = 3)
    @Parameter(required = true)
    private String lastName;
}
