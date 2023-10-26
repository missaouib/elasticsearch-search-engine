package com.richcode.search.query.predicate;

import com.richcode.search.dto.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

@RequiredArgsConstructor
class WildcardSearchPredicateFactory {

    private final SearchPredicateFactory predicateFactory;

    public SearchPredicate create(final SearchCriteria criteria) {
        if (criteria.value() instanceof String value) {
            return create(criteria.field(), value);
        }
        throw new IllegalArgumentException("Invalid argument type");
    }

    private SearchPredicate create(final String field, final String value) {
        return predicateFactory
            .wildcard()
            .field(field)
            .matching(value)
            .toPredicate();
    }

}
