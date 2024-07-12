package ru.semka.bookository.server.rest.dto.book;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.rest.dto.TableCriteria;
import ru.semka.bookository.server.validator.BookSortColumnValidator;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookCriteriaDto extends TableCriteria {

    @Parameter(in = ParameterIn.QUERY, description = "Фильтрация по имени карточки книги")
    private String name;

    @Parameter(in = ParameterIn.QUERY, description = "Фильтрация по имени автора книги")
    private String author;

    @Parameter(in = ParameterIn.QUERY, description = "Фильтрация по жанру")
    private String genre;

    @Parameter(in = ParameterIn.QUERY)
    private Language language;

    @Parameter(in = ParameterIn.QUERY)
    private Collection<Integer> categories;

    @Parameter(in = ParameterIn.QUERY)
    @Schema(
            allowableValues = {"id", "name", "genre", "author", "language", "createdAt"},
            description = "defines the sorting order of books"
    )
    @BookSortColumnValidator
    private String sortColumn;
}
