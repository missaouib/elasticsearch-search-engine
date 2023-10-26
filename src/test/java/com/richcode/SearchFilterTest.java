package com.richcode;

import com.richcode.domain.Author;
import com.richcode.domain.Book;
import com.richcode.domain.BookGenre;
import com.richcode.repository.BookRepository;
import com.richcode.search.dto.result.SearchResult;
import com.richcode.support.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static com.richcode.repository.BookSearchCriteriaRepository.BookSearchCriteria;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Transactional
@Testcontainers
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class, webEnvironment = RANDOM_PORT)
class SearchFilterTest {

    @Container
    private static final ElasticsearchContainer elasticsearch = ElasticsearchContainerFactory.create();

    @Autowired
    private BookRepository repository;

    @Test
    void shouldFindBooksWithIsbnIn() {
        // given
        var criteria = BookSearchCriteria.builder()
            .isbnIn(List.of("isbn-09"))
            .build();

        // when
        SearchResult<Book> result = repository.find(criteria);

        // then
        List<Book> content = result.getContent();
        assertThat(content).extracting(Book::getIsbn)
            .containsExactly("isbn-09");
    }

    @Test
    void shouldFindBooksWithTitleLike() {
        // given
        var criteria = BookSearchCriteria.builder()
            .titleLike("Book 1")
            .build();

        // when
        SearchResult<Book> result = repository.find(criteria);

        // then
        List<Book> content = result.getContent();
        assertThat(content).hasSize(9);
        assertThat(content).extracting(Book::getTitle)
            .allMatch(title -> title.startsWith("Book 1"));
    }

    @Test
    void shouldFindBooksWithBookGenreIn() {
        // given
        var criteria = BookSearchCriteria.builder()
            .genreIn(Set.of(BookGenre.FANTASY))
            .build();

        // when
        SearchResult<Book> result = repository.find(criteria);

        // then
        List<Book> content = result.getContent();
        assertThat(content).hasSize(3);
        assertThat(content).extracting(Book::getGenre)
            .containsOnly(BookGenre.FANTASY);
    }

    @Test
    void shouldFindBooksWithPublishedInRange() {
        // given
        var from = OffsetDateTime.parse("2023-10-01T00:00:00+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        var to = OffsetDateTime.parse("2023-10-03T00:00:00+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        var criteria = BookSearchCriteria.builder()
            .publishedRange(Range.range(from, to))
            .build();

        // when
        SearchResult<Book> result = repository.find(criteria);

        // then
        List<Book> content = result.getContent();
        assertThat(content).hasSize(3);
        assertThat(content).extracting(Book::getPublished)
            .allMatch(published -> !published.isBefore(from) && !published.isAfter(to));
    }

    @Test
    void shouldFindBooksWithPageCountInRange() {
        // given
        var from = 116L;
        var criteria = BookSearchCriteria.builder()
            .pageCountRange(Range.from(from))
            .build();

        // when
        SearchResult<Book> result = repository.find(criteria);

        // then
        List<Book> content = result.getContent();
        assertThat(content).hasSize(3);
        assertThat(content).extracting(Book::getPageCount)
            .allMatch(pageCount -> pageCount >= 116L);
    }

    @Test
    void shouldFindBooksWithAuthorsNameLike() {
        // given
        var criteria = BookSearchCriteria.builder()
            .authorsNameLike("Author A")
            .build();

        // when
        SearchResult<Book> result = repository.find(criteria);

        // then
        List<Book> content = result.getContent();
        assertThat(content).hasSize(6);
        assertThat(content)
            .allMatch(book -> book.getAuthors().stream().map(Author::getName).toList().contains("Author A"));
    }

    @Test
    void shouldFindBooksPage() {
        // given
        var criteria = BookSearchCriteria.builder()
            .page(Pageable.ofSize(6))
            .build();

        // when
        SearchResult<Book> result = repository.find(criteria);

        // then
        assertThat(result).hasSize(6);
        assertThat(result.getNumberOfElements()).isEqualTo(6);
        assertThat(result.getTotalElements()).isEqualTo(18);
        assertThat(result.getTotalPages()).isEqualTo(3);
        assertThat(result.hasPrevious()).isFalse();
        assertThat(result.hasNext()).isTrue();
        assertThat(result.isLast()).isFalse();
    }

}