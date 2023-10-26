package com.richcode.repository;

import com.richcode.domain.Book;
import com.richcode.search.SearchEngine;
import com.richcode.search.dto.search.Filter;
import com.richcode.search.dto.search.Query;
import com.richcode.search.dto.result.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class BookRepositoryImpl implements BookSearchCriteriaRepository {

    private final SearchEngine<Book> searchEngine;

    @Override
    public SearchResult<Book> find(final BookSearchCriteria searchCriteria) {
        final Pageable page = searchCriteria.page() != null ? searchCriteria.page() : Pageable.ofSize(10_000);
        final List<Filter> filters = BookSearchCriteriaFiltersBuilder.builder()
            .hasIsbnIn(searchCriteria.isbnIn())
            .hasTitleLike(searchCriteria.titleLike())
            .hasGenreIn(searchCriteria.genreIn())
            .hasPublishedInRange(searchCriteria.publishedRange())
            .hasPageCountInRange(searchCriteria.pageCountRange())
            .hasCreatedAtInRange(searchCriteria.createAtRange())
            .hasAuthorsNameLike(searchCriteria.authorsNameLike())
            .build();
        return searchEngine.search(Query.<Book>builder()
            .clazz(Book.class)
            .filters(filters)
            .pageable(page)
            .build());
    }

}
