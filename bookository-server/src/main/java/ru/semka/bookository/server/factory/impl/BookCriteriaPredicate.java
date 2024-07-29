package ru.semka.bookository.server.factory.impl;

import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.dao.entity.BookEntity_;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.dao.entity.CategoryEntity_;
import ru.semka.bookository.server.factory.CriteriaPredicateFactory;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class BookCriteriaPredicate implements CriteriaPredicateFactory<BookCriteriaDto, BookEntity> {
    @Override
    public PredicateProvider<BookEntity> create(BookCriteriaDto input) {

        return ((builder, root) -> {
            Collection<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(input.getName())
                    .ifPresent(value -> predicates.add(builder.like(
                            builder.lower(root.get(BookEntity_.name)),
                            ("%" + value + "%").toLowerCase()
                    )));

            Optional.ofNullable(input.getAuthor())
                    .ifPresent(value -> predicates.add(builder.like(
                            builder.lower(root.get(BookEntity_.author)),
                            ("%" + value + "%").toLowerCase()
                    )));

            Optional.ofNullable(input.getGenre())
                    .ifPresent(value -> predicates.add(builder.like(
                            builder.lower(root.get(BookEntity_.genre)),
                            ("%" + value + "%").toLowerCase()
                    )));

            Optional.ofNullable(input.getLanguage())
                    .ifPresent(value -> predicates.add(builder.equal(root.get(BookEntity_.language), value)));

            Optional.ofNullable(input.getCategories())
                    .ifPresent(value -> {
                        CriteriaQuery<BookEntity> bookCriteriaQuery = builder.createQuery(BookEntity.class);
                        Subquery<Integer> subquery = bookCriteriaQuery.subquery(Integer.class);
                        Root<BookEntity> subqueryCategory = subquery.from(BookEntity.class);
                        Join<CategoryEntity, BookEntity> subqueryCategories = subqueryCategory.join(BookEntity_.CATEGORIES);
                        subquery.select(subqueryCategory.get(BookEntity_.ID)).where(
                                subqueryCategories.get(CategoryEntity_.ID).in(value)
                        );
                        predicates.add(builder.in(root.get(CategoryEntity_.ID)).value(subquery));
                    });

            return predicates;
        });
    }
}
