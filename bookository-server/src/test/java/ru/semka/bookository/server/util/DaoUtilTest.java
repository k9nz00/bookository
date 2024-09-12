package ru.semka.bookository.server.util;

import org.junit.jupiter.api.Test;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.dto.SearchCriteriaDto;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.TableCriteria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class DaoUtilTest {
    @Test
    void createCriteria() {
        TableCriteria tableCriteria = mock(TableCriteria.class);
        PredicateProvider predicateProvider = mock(PredicateProvider.class);
        Class<BookEntity> entityClass = BookEntity.class;

        SearchCriteriaDto criteria = DaoUtil.createCriteria(tableCriteria, predicateProvider, entityClass);

        assertNotNull(criteria);
        assertEquals(entityClass, criteria.getClazz());

    }
}