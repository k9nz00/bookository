package ru.semka.bookository.server.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.dto.SearchCriteriaDto;
import ru.semka.bookository.server.rest.dto.TableCriteria;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DaoUtil {
    public static <T> SearchCriteriaDto<T> createCriteria(TableCriteria tableCriteria,
                                                          PredicateProvider<T> provider,
                                                          Class<T> clazz) {
        return new SearchCriteriaDto<>(
                clazz,
                provider,
                tableCriteria.getLimit(),
                tableCriteria.getOffset(),
                tableCriteria.getSortDirection(),
                tableCriteria.getSortColumn()
        );
    }
}
