package com.richcode.search.dto.result;

import lombok.Builder;

import java.util.Map;

@Builder
public record Aggregation(String name, String field, Map<String, Long> result) {

    public static Aggregation empty() {
        return Aggregation.builder()
            .result(Map.of())
            .build();
    }

    public Long getValue(final String key) {
        return result.getOrDefault(key, 0L);
    }

}
