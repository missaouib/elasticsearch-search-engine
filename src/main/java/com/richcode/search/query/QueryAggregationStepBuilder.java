package com.richcode.search.query;

import com.richcode.search.dto.search.Aggregator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.aggregation.AggregationKey;
import org.hibernate.search.engine.search.aggregation.SearchAggregation;
import org.hibernate.search.engine.search.aggregation.dsl.SearchAggregationFactory;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.mapper.orm.session.SearchSession;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class QueryAggregationStepBuilder {

    private final SearchAggregationFactory aggregationFactory;

    public static <T> QueryAggregationStepBuilder of(final @NonNull SearchSession session, final @NonNull Class<T> clazz) {
        return new QueryAggregationStepBuilder(session.scope(clazz).aggregation());
    }

    public List<AggregationStep> buildFor(final Collection<Aggregator> aggregators) {
        return aggregators.stream()
            .map(aggregator -> AggregationStep.builder()
                .aggregationKey(aggregator.aggregationKey())
                .aggregation(aggregationFor(aggregator.field()))
                .build())
            .toList();
    }

    private SearchAggregation<Map<String, Long>> aggregationFor(final String field) {
        return aggregationFactory.terms().field(field, String.class, ValueConvert.NO).toAggregation();
    }

    @Builder
    public record AggregationStep(AggregationKey<Map<String, Long>> aggregationKey,
                                  SearchAggregation<Map<String, Long>> aggregation) {
    }

}
