package ru.semka.bookository.server.rest.dto.book;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.semka.bookository.server.common.enums.Language;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class BookCsvDto {
    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "author")
    private String author;

    @CsvBindByName(column = "genre")
    private String genre;

    @CsvBindByName(column = "language")
    private Language language;

    @CsvBindByName(column = "annotation")
    private String annotation;

    @CsvBindAndSplitByName(column = "categories", required = true, elementType = Integer.class, splitOn = ",")
    private Set<Integer> categories;
}