package ru.semka.bookository.server.rest.dto.book;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.rest.dto.author.AuthorRequestDto;

import java.util.Collection;
import java.util.Set;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    @Schema(name = "name", requiredMode = REQUIRED, type = "string")
    @NotBlank
    @Length(min = 3)
    @Parameter(required = true)
    private String name;

    @Parameter
    private Collection<AuthorRequestDto> authors;

    @Parameter
    private String genre;

    @Parameter
    private Language language;

    @Parameter
    private String annotation;

    @Parameter
    private Set<Integer> categories;
}