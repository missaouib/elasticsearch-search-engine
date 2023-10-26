package com.richcode;

import com.richcode.domain.Author;
import com.richcode.domain.BookGenre;
import com.richcode.search.SearchEngine;
import com.richcode.search.dto.result.SearchResult;
import com.richcode.search.dto.search.Query;
import com.richcode.search.dto.search.SortFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Transactional
@Testcontainers
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class, webEnvironment = RANDOM_PORT)
class SearchSortTest {

    @Container
    private static final ElasticsearchContainer elasticsearch = ElasticsearchContainerFactory.create();

    @Autowired
    private SearchEngine<Author> searchEngineForAuthors;

    @Test
    void shouldSortAsc() {
        // given
        var query = Query.<Author>builder()
            .clazz(Author.class)
            .pageable(all(Sort.by(Direction.ASC, "name")))
            .build();

        // when
        SearchResult<Author> result = searchEngineForAuthors.search(query);

        // then
        assertThat(result.getContent()).extracting(Author::getName)
            .containsExactly("Author A", "Author B", "Author C");
    }

    @Test
    void shouldSortDesc() {
        // given
        var query = Query.<Author>builder()
            .clazz(Author.class)
            .pageable(all(Sort.by(Direction.DESC, "name")))
            .build();

        // when
        SearchResult<Author> result = searchEngineForAuthors.search(query);

        // then
        assertThat(result.getContent()).extracting(Author::getName)
            .containsExactly("Author C", "Author B", "Author A");
    }

    @Test
    void shouldSortByElementOfEmbeddedCollection() {
        // given
        var query = Query.<Author>builder()
            .clazz(Author.class)
            .sortFilters(List.of(SortFilter.builder()
                .sortBy("books.pageCount")
                .field("books.genre")
                .value(BookGenre.FANTASY)
                .build()))
            .pageable(all(Sort.by(Direction.ASC, "books.pageCount")))
            .build();

        // when
        SearchResult<Author> result = searchEngineForAuthors.search(query);

        // then
        assertThat(result.getContent()).extracting(Author::getName)
            .containsExactly("Author A", "Author B", "Author C");
    }

    private static Pageable all(final Sort sort) {
        return PageRequest.of(0, 10_000, sort);
    }

}
