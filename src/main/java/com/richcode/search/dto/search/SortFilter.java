package com.richcode.search.dto.search;

import lombok.Builder;

import java.util.Objects;

@Builder
public record SortFilter(String sortBy, String field, Object value) {

    public SortFilter {
        Objects.requireNonNull(sortBy);
        Objects.requireNonNull(field);
        Objects.requireNonNull(value);
    }

}
