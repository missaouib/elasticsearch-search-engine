package com.richcode.search.query;

import com.richcode.search.dto.search.Filter;
import com.richcode.search.query.predicate.PredicateFactory;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;

import java.util.Collection;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class QueryWhereStepBuilder {

    private final PredicateFactory predicateFactory;

    public static QueryWhereStepBuilder of(final @NonNull PredicateFactory predicateFactory) {
        return new QueryWhereStepBuilder(predicateFactory);
    }

    public SearchPredicate buildFor(final @NonNull Collection<Filter> filters) {
        return combineWithAnd(filters);
    }

    private SearchPredicate combineWithAnd(final Collection<Filter> filters) {
        final BooleanPredicateClausesStep<?> and = predicateFactory.bool();
        filters.forEach(filter -> and.must(combineWithOr(filter)));
        return and.toPredicate();
    }

    private SearchPredicate combineWithOr(final Filter filter) {
        final BooleanPredicateClausesStep<?> or = predicateFactory.bool();
        filter.forEach(matching -> or.should(predicateFactory.create(matching)));
        return or.toPredicate();
    }

}
