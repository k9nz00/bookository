package ru.semka.bookository.server.rest.dto.book;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.rest.dto.TableCriteria;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookCriteriaDto extends TableCriteria {

    @Parameter(
            in = ParameterIn.QUERY,
            description = "Фильтрация по имени карточки книги"
    )
    private String name;

    @Parameter(
            in = ParameterIn.QUERY,
            description = "Фильтрация по имени автора книги"
    )
    private String author;

    @Parameter(
            in = ParameterIn.QUERY,
            description = "Фильтрация по жанру"
    )
    private String genre;

    private Language language;

    private Collection<Integer> categories;

    @Parameter(
            in = ParameterIn.QUERY,
            schema = @Schema(allowableValues = {"id", "name", "genre", "author", "language", "createdAt"})
    )
    private String sortColumn;
}
