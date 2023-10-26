package com.richcode.search.query.predicate;

import com.richcode.search.dto.SearchCriteria;
import com.richcode.search.dto.SearchRange;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

@RequiredArgsConstructor
class RangeSearchPredicateFactory {

    private final SearchPredicateFactory predicateFactory;

    public SearchPredicate create(final SearchCriteria criteria) {
        if (criteria.value() instanceof SearchRange<?> range) {
            return create(criteria.field(), range);
        }
        throw new IllegalArgumentException("Value has to be SearchRange type");
    }

    private SearchPredicate create(final String field, final SearchRange<?> range) {
        if (range.isEmpty()) {
            throw new IllegalArgumentException("SearchRange value can not be empty");
        }
        return predicateFactory
            .range()
            .field(field)
            .between(range.from(), range.to())
            .toPredicate();
    }

}
