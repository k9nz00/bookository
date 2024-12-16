package ru.semka.bookository.server.rest.dto.book;

public record AuthorUiDto(
        Integer id,
        String firstName,
        String surName,
        String lastName
) {
}
