package com.richcode.search.query;

import com.richcode.search.dto.search.SortFilter;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.sort.SearchSort;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.engine.search.sort.dsl.SortFinalStep;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class QuerySortStepBuilder {

    private final SearchSortFactory sortFactory;

    public static <T> QuerySortStepBuilder of(final @NonNull SearchSession session, final @NonNull Class<T> clazz) {
        return new QuerySortStepBuilder(session.scope(clazz).sort());
    }

    public SearchSort buildFor(final @NonNull Sort sort, final @NonNull Collection<SortFilter> sortFilters) {
        return sort.isSorted()
            ? buildCompositeSort(sort, sortFilters)
            : buildIndexSort();
    }

    private SearchSort buildIndexSort() {
        return sortFactory.indexOrder().toSort();
    }

    private SearchSort buildCompositeSort(final Sort sort, final Collection<SortFilter> sortFilters) {
        final Map<String, SortFilter> sortFiltersBySortBy = sortFilters.stream()
            .collect(toMap(SortFilter::sortBy, Function.identity()));

        return sortFactory.composite(compositeSorting -> sort.forEach(order -> {
            final SortFilter sortFilter = sortFiltersBySortBy.get(order.getProperty());
            compositeSorting.add(sortFilter == null ? buildSortStep(order) : buildSortStep(order, sortFilter));
        })).toSort();
    }

    private SortFinalStep buildSortStep(final Sort.Order order) {
        return sortFactory
            .field(order.getProperty())
            .order(map(order.getDirection()));
    }

    private SortFinalStep buildSortStep(final Sort.Order order, final SortFilter sortFilter) {
        return sortFactory
            .field(order.getProperty())
            .filter(pfs -> pfs.match().field(sortFilter.field()).matching(sortFilter.value()))
            .order(map(order.getDirection()));
    }

    private static SortOrder map(final Sort.Direction direction) {
        return switch (direction) {
            case ASC -> SortOrder.ASC;
            case DESC -> SortOrder.DESC;
        };
    }

}
