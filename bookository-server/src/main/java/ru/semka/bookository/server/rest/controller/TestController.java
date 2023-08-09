package ru.semka.bookository.server.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Tag(name = "test controller name", description = "test controller description")
public class TestController {

    @GetMapping("/api/test/hello")
    public String testMethod() {
        return "Hello. Current time is " + LocalDateTime.now();
    }
}
