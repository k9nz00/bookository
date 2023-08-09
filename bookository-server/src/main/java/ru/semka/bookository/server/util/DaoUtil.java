package ru.semka.bookository.server.util;

import lombok.experimental.UtilityClass;
import ru.semka.bookository.server.dao.dto.SearchCriteriaDto;
import ru.semka.bookository.server.rest.dto.TableCriteria;

@UtilityClass
public class DaoUtil {
    public static <T> SearchCriteriaDto<T> createCriteria(TableCriteria tableCriteria, Class<T> clazz) {
        return new SearchCriteriaDto<>(
                clazz,
                tableCriteria.getLimit(),
                tableCriteria.getOffset(),
                tableCriteria.getSortDirection(),
                tableCriteria.getSortColumn()
        );
    }
}
