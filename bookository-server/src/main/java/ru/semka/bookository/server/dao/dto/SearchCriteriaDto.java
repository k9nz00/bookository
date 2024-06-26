package ru.semka.bookository.server.dao.dto;

import lombok.Data;
import ru.semka.bookository.server.dao.PredicateProvider;

@Data
public class SearchCriteriaDto<T> {
    private final Class<T> clazz;
    private final PredicateProvider<T> provider;
    private final Integer limit;
    private final Integer offset;
    private final String sortDirection;
    private final String sortColumn;
}
