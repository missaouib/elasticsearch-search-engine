package com.richcode.search;

import com.richcode.search.dto.result.Aggregation;
import com.richcode.search.dto.result.SearchResult;
import com.richcode.search.dto.search.Query;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class SearchResultConverter {

    public static <T> SearchResult<T> convert(org.hibernate.search.engine.search.query.SearchResult<T> res, Query<T> query) {
        return SearchResult.<T>builder()
            .content(res.hits())
            .pageable(query.pageable())
            .total(res.total().hitCount())
            .aggregations(query.aggregators().stream()
                .map(aggregator -> Aggregation.builder()
                    .name(aggregator.name())
                    .field(aggregator.getFieldWithoutSuffix())
                    .result(res.aggregation(aggregator.aggregationKey()))
                    .build())
                .toList())
            .build();
    }

}
