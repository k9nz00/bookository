package ru.semka.bookository.server.factory.impl;

import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.entity.BookWithSmallPreviewEntity;
import ru.semka.bookository.server.dao.entity.BookWithSmallPreviewEntity_;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.dao.entity.CategoryEntity_;
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
                    .ifPresent(value -> predicates.add(builder.equal(root.get(BookWithSmallPreviewEntity_.name), value)));

            Optional.ofNullable(input.getAuthor())
                    .ifPresent(value -> predicates.add(builder.like(root.get(BookWithSmallPreviewEntity_.author), "%" + value + "%")));

            Optional.ofNullable(input.getGenre())
                    .ifPresent(value -> predicates.add(builder.like(root.get(BookWithSmallPreviewEntity_.genre), "%" + value + "%")));

            Optional.ofNullable(input.getLanguage())
                    .ifPresent(value -> predicates.add(builder.equal(root.get(BookWithSmallPreviewEntity_.language), value)));

            Optional.ofNullable(input.getCategories())
                    .ifPresent(value -> {
                        CriteriaQuery<BookWithSmallPreviewEntity> bookCriteriaQuery = builder.createQuery(BookWithSmallPreviewEntity.class);
                        Subquery<Integer> subquery = bookCriteriaQuery.subquery(Integer.class);
                        Root<BookWithSmallPreviewEntity> subqueryCategory = subquery.from(BookWithSmallPreviewEntity.class);
                        Join<CategoryEntity, BookWithSmallPreviewEntity> subqueryCategories = subqueryCategory.join(BookWithSmallPreviewEntity_.CATEGORIES);
                        subquery.select(subqueryCategory.get(BookWithSmallPreviewEntity_.ID)).where(
                                subqueryCategories.get(CategoryEntity_.ID).in(value)
                        );
                        predicates.add(builder.in(root.get(CategoryEntity_.ID)).value(subquery));
                    });

            return predicates;
        });
    }
}
