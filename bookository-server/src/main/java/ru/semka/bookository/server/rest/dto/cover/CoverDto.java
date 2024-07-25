package ru.semka.bookository.server.rest.dto.cover;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public record CoverDto(String imageData) {
}
