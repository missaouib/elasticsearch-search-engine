package com.richcode;

import com.richcode.domain.Book;
import com.richcode.domain.BookGenre;
import com.richcode.domain.Book_;
import com.richcode.search.SearchEngine;
import com.richcode.search.dto.SearchRange;
import com.richcode.search.dto.result.Aggregation;
import com.richcode.search.dto.result.SearchResult;
import com.richcode.search.dto.search.Aggregator;
import com.richcode.search.dto.search.Filter;
import com.richcode.search.dto.search.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static com.richcode.domain.BookGenre.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Transactional
@Testcontainers
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class, webEnvironment = RANDOM_PORT)
class SearchAggregatorTest {

    @Container
    private static final ElasticsearchContainer elasticsearch = ElasticsearchContainerFactory.create();

    @Autowired
    private SearchEngine<Book> searchEngineForBooks;

    @Test
    void shouldAggregateBookGenre() {
        // when
        SearchResult<Book> result = searchEngineForBooks.search(Query.<Book>builder()
            .clazz(Book.class)
            .aggregators(List.of(Aggregator.of("aggregator-genre", "genre")))
            .build());

        // then
        Aggregation aggregation = result.getAggregations().get(0);
        assertThat(aggregation.name()).isEqualTo("aggregator-genre");
        assertThat(aggregation.field()).isEqualTo("genre");
        assertThat(aggregation.result()).isEqualTo(Map.of(
            FANTASY.name(),            3L,
            SCIENCE_FICTION.name(),    3L,
            DYSTOPIAN.name(),          3L,
            ADVENTURE.name(),          3L,
            HORROR.name(),             3L,
            THRILLER.name(),           3L
        ));
        assertThat(aggregation.getValue(ROMANCE.name())).isEqualTo(0);
        assertThat(aggregation.getValue(DETECTIVE.name())).isEqualTo(0);
    }

    @Test
    void shouldAggregateBookGenreOnlyFiltered() {
        // when
        SearchResult<Book> result = searchEngineForBooks.search(Query.<Book>builder()
            .clazz(Book.class)
            .filters(List.of(Filter.range("pageCount", SearchRange.range(101L, 106L))))
            .aggregators(List.of(Aggregator.of("aggregator-genre", "genre")))
            .build());

        // then
        Aggregation aggregation = result.getAggregations().get(0);
        assertThat(aggregation.result()).isEqualTo(Map.of(
            FANTASY.name(),            3L,
            SCIENCE_FICTION.name(),    3L
        ));
    }

}
