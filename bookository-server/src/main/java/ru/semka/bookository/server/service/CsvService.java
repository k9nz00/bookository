package ru.semka.bookository.server.service;

import java.io.Reader;
import java.nio.file.Path;
import java.util.List;

public interface CsvService {
    <T> List<T> parseCsv(Path path, Class<T> clazz);

    <T> List<T> parseCsv(Reader reader, Class<T> clazz);
}
