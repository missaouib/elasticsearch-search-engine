package com.richcode.search.dto.search;

import com.richcode.search.dto.SearchRange;
import com.richcode.search.dto.SearchType;
import com.richcode.search.dto.SearchCriteria;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public record Filter(SearchType searchType, Map<String, Collection<?>> should) {

    public Filter {
        Objects.requireNonNull(searchType);
        should = Objects.requireNonNullElseGet(should, Map::of);
    }

    public static Filter keyword(final String field, final Object value) {
        return new Filter(SearchType.KEYWORD, Map.of(field, List.of(value)));
    }

    public static Filter keyword(final String field, final Collection<?> values) {
        return new Filter(SearchType.KEYWORD, Map.of(field, values));
    }

    public static Filter wildcard(final String field, final String value) {
        return new Filter(SearchType.WILDCARD, Map.of(field, List.of("*" + value + "*")));
    }

    public static Filter wildcardLeft(final String field, final String value) {
        return new Filter(SearchType.WILDCARD, Map.of(field, List.of("*" + value)));
    }

    public static Filter wildcardRight(final String field, final String value) {
        return new Filter(SearchType.WILDCARD, Map.of(field, List.of(value + "*")));
    }

    public static Filter wildcard(final String field, final Collection<String> values) {
        return new Filter(SearchType.WILDCARD, Map.of(field, values.stream().map(val -> "*" + val + "*").toList()));
    }

    public static Filter wildcardLeft(final String field, final Collection<String> values) {
        return new Filter(SearchType.WILDCARD, Map.of(field, values.stream().map(val -> "*" + val).toList()));
    }

    public static Filter wildcardRight(final String field, final Collection<String> values) {
        return new Filter(SearchType.WILDCARD, Map.of(field, values.stream().map(val -> val + "*").toList()));
    }

    public static <T> Filter range(final String field, final SearchRange<T> value) {
        return new Filter(SearchType.RANGE, Map.of(field, List.of(value)));
    }

    public static <T> Filter range(final String field, final Collection<SearchRange<T>> values) {
        return new Filter(SearchType.RANGE, Map.of(field, values));
    }

    public boolean isEmpty() {
        return should.isEmpty();
    }

    public void forEach(final Consumer<SearchCriteria> consumer) {
        should.entrySet().stream()
            .map(entry -> entry.getValue().stream()
                .map(val -> SearchCriteria.builder()
                    .type(searchType)
                    .field(entry.getKey())
                    .value(val)
                    .build())
                .toList())
            .flatMap(Collection::stream)
            .forEach(consumer);
    }

    public void forEach(final BiConsumer<String, Collection<?>> consumer) {
        should.forEach(consumer);
    }
}
