package com.richcode.repository;

import com.richcode.domain.Author_;
import com.richcode.domain.BookGenre;
import com.richcode.domain.Book_;
import com.richcode.search.dto.SearchRange;
import com.richcode.search.dto.search.Filter;
import com.richcode.support.EntityFieldPathBuilder;
import com.richcode.support.Range;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class BookSearchCriteriaFiltersBuilder {

    private static final String AUTHORS_NAME =EntityFieldPathBuilder.instance()
        .add(Book_.AUTHORS)
        .add(Author_.NAME)
        .build();

    private final List<Filter> filters = new LinkedList<>();

    public static BookSearchCriteriaFiltersBuilder builder() {
        return new BookSearchCriteriaFiltersBuilder();
    }

    public BookSearchCriteriaFiltersBuilder hasIsbnIn(final Collection<String> isbns) {
        if (CollectionUtils.isEmpty(isbns)) {
            return this;
        }
        filters.add(Filter.keyword(Book_.ISBN, isbns));
        return this;
    }

    public BookSearchCriteriaFiltersBuilder hasTitleLike(final String title) {
        if (!StringUtils.hasText(title)) {
            return this;
        }
        filters.add(Filter.wildcard(Book_.TITLE, title));
        return this;
    }

    public BookSearchCriteriaFiltersBuilder hasGenreIn(final Collection<BookGenre> genres) {
        if (CollectionUtils.isEmpty(genres)) {
            return this;
        }
        filters.add(Filter.keyword(Book_.GENRE, genres));
        return this;
    }

    public BookSearchCriteriaFiltersBuilder hasPublishedInRange(final Range<OffsetDateTime> range) {
        if (range == null || range.isEmpty()) {
            return this;
        }
        filters.add(Filter.range(Book_.PUBLISHED, new SearchRange<>(range.from(), range.to())));
        return this;
    }

    public BookSearchCriteriaFiltersBuilder hasPageCountInRange(final Range<Long> range) {
        if (range == null || range.isEmpty()) {
            return this;
        }
        filters.add(Filter.range(Book_.PAGE_COUNT, new SearchRange<>(range.from(), range.to())));
        return this;
    }

    public BookSearchCriteriaFiltersBuilder hasCreatedAtInRange(final Range<OffsetDateTime> range) {
        if (range == null || range.isEmpty()) {
            return this;
        }
        filters.add(Filter.range(Book_.CREATED_AT, new SearchRange<>(range.from(), range.to())));
        return this;
    }

    public BookSearchCriteriaFiltersBuilder hasAuthorsNameLike(final String name) {
        if (!StringUtils.hasText(name)) {
            return this;
        }
        filters.add(Filter.wildcard(AUTHORS_NAME, name));
        return this;
    }

    public List<Filter> build() {
        return filters;
    }
}
