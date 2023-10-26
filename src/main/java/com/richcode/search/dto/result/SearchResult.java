package com.richcode.search.dto.result;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.function.Function;

@Getter
@EqualsAndHashCode(callSuper = true)
public class SearchResult<T> extends PageImpl<T> implements Page<T> {

    private final List<Aggregation> aggregations;

    public SearchResult(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        this.aggregations = List.of();
    }

    @Builder
    public SearchResult(List<T> content, Pageable pageable, long total, List<Aggregation> aggregations) {
        super(content, pageable, total);
        this.aggregations = aggregations;
    }

    public <U> SearchResult<U> map(@NonNull Function<? super T, ? extends U> converter) {
        return SearchResult.<U>builder()
            .content(this.getConvertedContent(converter))
            .pageable(this.getPageable())
            .total(this.getTotalElements())
            .aggregations(this.getAggregations())
            .build();
    }
}
