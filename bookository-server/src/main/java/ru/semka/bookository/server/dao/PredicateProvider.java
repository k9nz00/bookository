package ru.semka.bookository.server.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.Collection;

@FunctionalInterface
public interface PredicateProvider<T> {
    Collection<Predicate> getPredicates(CriteriaBuilder builder, Root<T> root);
}
