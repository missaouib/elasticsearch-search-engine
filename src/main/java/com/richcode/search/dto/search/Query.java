package com.richcode.search.dto.search;

import lombok.Builder;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

@Builder
public record Query<T>(Class<T> clazz,
                       List<Filter> filters,
                       List<SortFilter> sortFilters,
                       List<Aggregator> aggregators,
                       Pageable pageable) {

    public Query {
        Objects.requireNonNull(clazz);
        filters = Objects.requireNonNullElseGet(filters, List::of);
        sortFilters = Objects.requireNonNullElseGet(sortFilters, List::of);
        aggregators = Objects.requireNonNullElseGet(aggregators, List::of);
        pageable = Objects.requireNonNullElseGet(pageable, () -> Pageable.ofSize(10_000));
    }

    public static <T> Query<T> of(final Class<T> clazz, final List<Filter> filters) {
        return Query.<T>builder()
            .clazz(clazz)
            .filters(filters)
            .build();
    }

}
