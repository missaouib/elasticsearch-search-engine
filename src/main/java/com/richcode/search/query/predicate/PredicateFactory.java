package com.richcode.search.query.predicate;

import com.richcode.search.dto.SearchCriteria;
import lombok.NonNull;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;

public class PredicateFactory {

    private final SearchPredicateFactory predicateFactory;
    private final KeywordSearchPredicateFactory keywordPredicateFactory;
    private final WildcardSearchPredicateFactory wildcardPredicateFactory;
    private final RangeSearchPredicateFactory rangePredicateFactory;

    public PredicateFactory(@NonNull final SearchPredicateFactory predicateFactory) {
        this.predicateFactory = predicateFactory;
        this.keywordPredicateFactory = new KeywordSearchPredicateFactory(predicateFactory);
        this.wildcardPredicateFactory = new WildcardSearchPredicateFactory(predicateFactory);
        this.rangePredicateFactory = new RangeSearchPredicateFactory(predicateFactory);
    }

    public SearchPredicate create(final SearchCriteria criteria) {
        return switch (criteria.type()) {
            case KEYWORD -> keywordPredicateFactory.create(criteria);
            case WILDCARD -> wildcardPredicateFactory.create(criteria);
            case RANGE -> rangePredicateFactory.create(criteria);
        };
    }

    public BooleanPredicateClausesStep<?> bool() {
        return predicateFactory.bool();
    }
}
