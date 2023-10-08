package ru.semka.bookository.server.rest.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class TableCriteria {
    @Max(50)
    @Parameter(
            in = ParameterIn.QUERY,
            schema = @Schema(example = "5",
                    defaultValue = "10",
                    description = "Result size limit"),
            description = "Result size limit")
    private Integer limit;

    @Parameter(
            in = ParameterIn.QUERY,
            schema = @Schema(example = "10",
                    defaultValue = "0",
                    description = "Slice of the first item in the collection to return"))
    private Integer offset;

    @Parameter(
            in = ParameterIn.QUERY,
            schema = @Schema(example = "ASC",
                    defaultValue = "ASC",
                    allowableValues = {"ASC", "DESC"},
                    description = "Sorting direction")
    )
    @Pattern(regexp = "^(asc|desc|ASC|DESC)", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String sortDirection;

    public abstract String getSortColumn();
}
