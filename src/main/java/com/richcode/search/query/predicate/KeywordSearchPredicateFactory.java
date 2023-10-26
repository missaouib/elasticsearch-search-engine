package com.richcode.search.query.predicate;

import com.richcode.search.dto.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

@RequiredArgsConstructor
class KeywordSearchPredicateFactory {

    private final SearchPredicateFactory predicateFactory;

    public SearchPredicate create(final SearchCriteria criteria) {
        return predicateFactory
                .match()
                .field(criteria.field())
                .matching(criteria.value())
                .toPredicate();
    }

}
