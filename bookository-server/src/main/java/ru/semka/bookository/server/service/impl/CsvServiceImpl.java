package ru.semka.bookository.server.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.semka.bookository.server.service.CsvService;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CsvServiceImpl implements CsvService {
    @Override
    public <T> List<T> parseCsv(Path path, Class<T> clazz) {
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader)
                    .withSeparator(';')
                    .withType(clazz)
                    .build();
            return cb.parse();
        } catch (IOException exception) {
            log.error("Ошибка при чтении файла для парсинга %s".formatted(path.getFileName()), exception);
            return Collections.emptyList();
        }
    }

    @Override
    public <T> List<T> parseCsv(Reader reader, Class<T> clazz) {
        CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader)
                .withSeparator(';')
                .withType(clazz)
                .build();
        return cb.parse();
    }
}
