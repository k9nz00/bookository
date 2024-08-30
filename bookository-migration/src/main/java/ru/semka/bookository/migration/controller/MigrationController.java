package ru.semka.bookository.migration.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.*;
import ru.semka.bookository.migration.dto.MigrationUpdateContext;
import ru.semka.bookository.migration.dto.UpdateRequestDto;
import ru.semka.bookository.migration.model.MigrationDbProfile;
import ru.semka.bookository.migration.service.MigrationService;

@RestController
@RequestMapping("/api/v1/migration")
@RequiredArgsConstructor
@Slf4j
public class MigrationController {

    private final MigrationService migrationService;

    @PostMapping("/{profileId}/update")
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
    public void rollback(@PathVariable("profileId") MigrationDbProfile profileId) {
        throw new NotImplementedException();
    }
}
