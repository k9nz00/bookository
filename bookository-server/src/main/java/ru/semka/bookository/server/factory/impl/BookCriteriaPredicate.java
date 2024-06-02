package ru.semka.bookository.server.factory.impl;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.entity.BookWithSmallPreviewEntity;
import ru.semka.bookository.server.factory.CriteriaPredicateFactory;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class BookCriteriaPredicate implements CriteriaPredicateFactory<BookCriteriaDto, BookWithSmallPreviewEntity> {
    @Override
    public PredicateProvider<BookWithSmallPreviewEntity> create(BookCriteriaDto input) {

        return ((builder, root) -> {
            Collection<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(input.getName())
                    .ifPresent(value -> predicates.add(builder.equal(root.get("name"), value)));

            return predicates;
        });
    }
}
