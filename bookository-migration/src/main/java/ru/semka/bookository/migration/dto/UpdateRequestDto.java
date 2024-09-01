package ru.semka.bookository.migration.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRequestDto {

    @NotBlank(message = "Jdbc url must not be null or empty")
    private String jdbcUrl;

    @NotNull(message = "Database must not be null")
    private String database;

    @NotNull(message = "Username must not be null")
    private String username;

    private String password;

    private boolean releaseLocks;
}
