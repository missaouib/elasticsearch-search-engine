package com.richcode.search.query;

import com.richcode.search.dto.search.Aggregator;
import com.richcode.search.dto.search.Filter;
import com.richcode.search.dto.search.SortFilter;
import com.richcode.search.query.predicate.PredicateFactory;
import lombok.AccessLevel;
import lombok.Builder;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.query.dsl.SearchQueryOptionsStep;
import org.hibernate.search.engine.search.sort.SearchSort;
import org.hibernate.search.mapper.orm.search.loading.dsl.SearchLoadingOptionsStep;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

import static com.richcode.search.query.QueryAggregationStepBuilder.AggregationStep;
import static org.springframework.util.CollectionUtils.isEmpty;

@Builder(access = AccessLevel.PRIVATE)
public class SearchQueryBuilder<T> {

    private final Class<T> clazz;
    private final SearchSession session;
    private final PredicateFactory predicateFactory;

    private Collection<Filter> filters;
    private Collection<Aggregator> aggregators;
    private Sort sort;
    private Collection<SortFilter> sortFilters;

    private SearchQueryOptionsStep<?, T, SearchLoadingOptionsStep, ?, ?> searchQuery;

    public static <T> SearchQueryBuilder<T> of(final SearchSession session, final Class<T> clazz) {
        return SearchQueryBuilder.<T>builder()
            .session(session)
            .clazz(clazz)
            .predicateFactory(new PredicateFactory(session.scope(clazz).predicate()))
            .build();
    }

    public SearchQueryBuilder<T> filters(final Collection<Filter> filters) {
        this.filters = filters;
        return this;
    }

    public SearchQueryBuilder<T> aggregators(final Collection<Aggregator> aggregators) {
        this.aggregators = aggregators;
        return this;
    }

    public SearchQueryBuilder<T> sort(final Sort sort, final Collection<SortFilter> sortFilters) {
        this.sort = sort;
        this.sortFilters = sortFilters;
        return this;
    }

    public SearchQueryOptionsStep<?, T, SearchLoadingOptionsStep, ?, ?> build() {
        applyFilters();
        applyAggregators();
        applySort();

        return searchQuery;
    }

    private void applyFilters() {
        if (isEmpty(filters)) {
            searchQuery = session.search(clazz).where(SearchPredicateFactory::matchAll);
        } else {
            SearchPredicate searchPredicate = QueryWhereStepBuilder.of(predicateFactory).buildFor(filters);
            searchQuery = session.search(clazz).where(searchPredicate);
        }
    }

    private void applyAggregators() {
        if (isEmpty(aggregators)) {
            return;
        }

        List<AggregationStep> aggregationSteps = QueryAggregationStepBuilder.of(session, clazz).buildFor(aggregators);
        for (AggregationStep step : aggregationSteps) {
            searchQuery = searchQuery.aggregation(step.aggregationKey(), step.aggregation());
        }
    }

    private void applySort() {
        if (sort == null) {
            return;
        }
        SearchSort sortStep = QuerySortStepBuilder.of(session, clazz).buildFor(sort, sortFilters);
        searchQuery = searchQuery.sort(sortStep);
    }

}
