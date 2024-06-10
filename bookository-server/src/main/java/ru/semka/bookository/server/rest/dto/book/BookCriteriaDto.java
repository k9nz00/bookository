package ru.semka.bookository.server.rest.dto.book;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.semka.bookository.server.rest.dto.TableCriteria;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookCriteriaDto extends TableCriteria {

    private String name;

    @Parameter(
            in = ParameterIn.QUERY,
            schema = @Schema(allowableValues = {"id", "name", "genre", "author", "language", "createdAt"}))
    private String sortColumn;
}
