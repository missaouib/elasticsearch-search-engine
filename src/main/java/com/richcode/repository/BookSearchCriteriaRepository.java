package com.richcode.repository;

import com.richcode.domain.Book;
import com.richcode.domain.BookGenre;
import com.richcode.search.dto.result.SearchResult;
import com.richcode.support.Range;
import lombok.Builder;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.Collection;

public interface BookSearchCriteriaRepository {

    SearchResult<Book> find(final BookSearchCriteria searchCriteria);

    @Builder
    record BookSearchCriteria(
        Collection<String> isbnIn,
        String titleLike,
        Collection<BookGenre> genreIn,
        Range<OffsetDateTime> publishedRange,
        Range<Long> pageCountRange,
        Range<OffsetDateTime> createAtRange,
        String authorsNameLike,
        Pageable page
    ) {}
}
