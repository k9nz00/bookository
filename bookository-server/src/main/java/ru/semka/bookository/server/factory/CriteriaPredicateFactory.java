package ru.semka.bookository.server.factory;

import ru.semka.bookository.server.dao.PredicateProvider;

public interface CriteriaPredicateFactory<I, U> {
    PredicateProvider<U> create(I input);
}
