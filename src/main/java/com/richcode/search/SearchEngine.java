package com.richcode.search;

import com.richcode.search.dto.result.SearchResult;
import com.richcode.search.dto.search.Filter;
import com.richcode.search.dto.search.Query;
import com.richcode.search.query.SearchQueryBuilder;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.mapper.orm.Search;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchEngine<T> {

    private final EntityManager em;

    @Transactional(propagation = Propagation.MANDATORY)
    public List<T> search(final Class<T> clazz, final List<Filter> filters) {
        return search(Query.of(clazz, filters)).getContent();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public SearchResult<T> search(final Query<T> query) {
        var res = SearchQueryBuilder.of(Search.session(em), query.clazz())
            .filters(query.filters())
            .aggregators(query.aggregators())
            .sort(query.pageable().getSort(), query.sortFilters())
            .build()
            .fetch((int) query.pageable().getOffset(), query.pageable().getPageSize());
        return SearchResultConverter.convert(res, query);
    }

}
