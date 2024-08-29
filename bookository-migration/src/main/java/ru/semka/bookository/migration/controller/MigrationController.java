package ru.semka.bookository.migration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.semka.bookository.migration.model.MigrationDbProfile;

@RestController
@RequestMapping("/api/v1/migration")
@Slf4j
public class MigrationController {

    @PostMapping("/{profileId}/update")
    public void update(@PathVariable("profileId") MigrationDbProfile profileId) {
        log.error(String.valueOf(profileId));
    }

    @PostMapping("/{profileId}/rollback")
    public void rollback(@PathVariable("profileId") MigrationDbProfile profileId) {

    }
}
