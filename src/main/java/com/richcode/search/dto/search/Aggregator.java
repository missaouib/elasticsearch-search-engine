package com.richcode.search.dto.search;

import org.hibernate.search.engine.search.aggregation.AggregationKey;

import java.util.Map;

public record Aggregator(String field, AggregationKey<Map<String, Long>> aggregationKey) {

    public static final String AGGREGABLE_FIELD_SUFFIX = "-aggregable";

    public static Aggregator of(final String name, final String field) {
        return new Aggregator(field, AggregationKey.of(name));
    }

    public String name() {
        return aggregationKey.name();
    }

    // todo rename
    public String getFieldWithoutSuffix() {
        return field.endsWith(AGGREGABLE_FIELD_SUFFIX)
            ? field.substring(0, field.length() - AGGREGABLE_FIELD_SUFFIX.length())
            : field;
    }

}
