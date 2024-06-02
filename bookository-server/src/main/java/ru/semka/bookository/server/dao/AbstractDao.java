package ru.semka.bookository.server.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.semka.bookository.server.dao.dto.SearchCriteriaDto;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public abstract class AbstractDao {
    protected final EntityManager entityManager;

    protected <T> Collection<T> execute(SearchCriteriaDto<T> searchCriteriaDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(searchCriteriaDto.getClazz());
        Root<T> root = criteriaQuery.from(searchCriteriaDto.getClazz());

        Optional.ofNullable(searchCriteriaDto.getProvider())
                .map(provider -> provider.getPredicates(criteriaBuilder, root).toArray(new Predicate[0]))
                .ifPresent(criteriaQuery::where);

        Optional.ofNullable(searchCriteriaDto.getSortColumn())
                .ifPresent(sort -> {
                    String[] paths = sort.split("\\.");
                    //создать объект сортировки
                    Path<Object> sortExpression = root.get(paths[0]);
                    for (int i = 1; i < paths.length; i++) {
                        //при сортировке по полю внутри смапленного (join) поля оно вызывается на первом объекте
                        //и добавляется (не заменяется) к значению первого
                        sortExpression = sortExpression.get(paths[i]);
                    }
                    Order order = "DESC".equalsIgnoreCase(searchCriteriaDto.getSortDirection())
                            ? criteriaBuilder.desc(sortExpression)
                            : criteriaBuilder.asc(sortExpression);
                    criteriaQuery.orderBy(order);
                });

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setMaxResults(searchCriteriaDto.getLimit() != null ? searchCriteriaDto.getLimit() : 10); //todo create default value
        query.setFirstResult(searchCriteriaDto.getOffset() != null ? searchCriteriaDto.getOffset() : 0); //todo create default value
        return query.getResultList();
    }
}
