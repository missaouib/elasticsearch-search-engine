package com.richcode.search.dto;

import lombok.Builder;

@Builder
public record SearchCriteria(SearchType type, String field, Object value) {
}
