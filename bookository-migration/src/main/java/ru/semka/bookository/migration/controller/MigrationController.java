package ru.semka.bookository.migration.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.semka.bookository.migration.dto.MigrationRollbackContext;
import ru.semka.bookository.migration.dto.MigrationUpdateContext;
import ru.semka.bookository.migration.dto.RollbackRequestDto;
import ru.semka.bookository.migration.dto.UpdateRequestDto;
import ru.semka.bookository.migration.enums.MigrationDbProfile;
import ru.semka.bookository.migration.service.MigrationService;

@RestController
@RequestMapping("/api/v1/migration")
@RequiredArgsConstructor
@Slf4j
public class MigrationController {

    private final MigrationService migrationService;

    @PostMapping("/{profileId}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("profileId") MigrationDbProfile dbProfile,
                       @RequestBody UpdateRequestDto requestDto) {
        MigrationUpdateContext context = new MigrationUpdateContext(
                dbProfile,
                requestDto.getJdbcUrl(),
                requestDto.getDatabase(),
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.isReleaseLocks()
        );
        migrationService.update(context);
    }

    @PostMapping("/{profileId}/rollback")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rollback(@PathVariable("profileId") MigrationDbProfile dbProfile,
                         @RequestBody RollbackRequestDto requestDto) {
        MigrationRollbackContext context = new MigrationRollbackContext(
                dbProfile,
                requestDto.getJdbcUrl(),
                requestDto.getDatabase(),
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.isReleaseLocks(),
                requestDto.getTag()
        );
        migrationService.rollback(context);
    }
}
