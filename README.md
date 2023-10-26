# Search engine  for Elasticsearch

## General info

This search engine was built for easy creation of queries to Elasticsearch to search through indexed data.
It provides a generic way of filtering, sorting and aggregating data.

As Hibernate Search 6 API does not provide a flexible manner of mixing and combining filter predicates on the fly,
this search engine provides the ability to create search criteria based on a user's current needs and
allows to easy switch/combine the criteria in each call.

## Technologies
- [Java 21](https://openjdk.org/projects/jdk/21/)
- [SpringBoot 3](https://spring.io/projects/spring-boot)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Hibernate Search 6.2](https://docs.jboss.org/hibernate/stable/search/reference/en-US/html_single/)
- [Testcontainers](https://testcontainers.com)
- [Elasticsearch 7](https://www.elastic.co/guide/en/elasticsearch/reference/7.17/index.html)
- [Lombok](https://projectlombok.org)

## Setup
To run the app you need to run an Elasticsearch container on the host machine
(a simple definition of the container is provided in the `docker-compose.yml` and a connection config in the `application.yml`).

But you can find some examples of usage in test classes.
- SearchFilterTest
- SearchSortTest
- SearchAggregatorTest

The tests use [Testcontainers](https://testcontainers.com), so **remember to provide a running docker daemon on a host machine**.
Then during the run of tests, `ElasticsearchContainerFactory` will provide a docker container with Elasticsearch
for the tests' context.

The search engine classes are placed in the `com.richcode.search` package and play around with one main public class
called `SearchEngine`. Additionally, all needed dtos used as data input for our query and results
are placed in `com.richcode.search.dto`.

Project provides a simple domain of objects to support showing examples of usages in tests.
It contains books and their authors in a many-to-many relation.
For more information, check `com.richcode.domain` package.

The app uses [H2 Database](https://www.h2database.com/html/main.html) as an in-memory storage.
Initial data are loaded at the start of Spring Context from `/resources/dara.sql` file.

**Remember to always use the search engine from a transactional context.**

## Code examples

You can find more examples of usage in the test classes.

### Creation of `SearchEngine` for an entity

`SearchEngine` is declared as a generic spring component and
needs a reference to `EntityManager` which is provided by the Spring Context.

Inject the Spring bean in one of many available ways and specify an entity class.

```java
@Autowired
private SearchEngine<Book> searchEngine;
```

### Filtering data

The list of filters is combined by the `and` operator and
values which need to be met for one filter object are combined with the `or` operator.

```java
SearchResult<Book> result = searchEngineForBooks.search(Query.<Book>builder()
    .clazz(Book.class)
    .filters(List.of(
        Filter.keyword(Book_.GENRE, List.of(BookGenre.FANTASY, BookGenre.SCIENCE_FICTION)),
        Filter.range(Book_.PUBLISHED, SearchRange.range(from, to))
    ))
    .build());
```

### Sorting data

Provide a `Pageable` object to sort data.

```java
PageRequest page = PageRequest.of(0, 10, Sort.by(Direction.ASC, "name"));
SearchResult<Author> result = searchEngineForAuthors.search(Query.<Author>builder()
    .clazz(Author.class)
    .pageable(page)
    .build());
```

When you need to sort objects by an element of an embedded collection then use `SortFilter`.
In the example below, authors are sorted by a page count of their books but taking into consideration only fantasy books.

```java
SearchResult<Author> result = searchEngineForAuthors.search(Query.<Author>builder()
    .clazz(Author.class)
    .sortFilters(List.of(SortFilter.builder()
        .sortBy("books.pageCount")
        .field("books.genre")
        .value(BookGenre.FANTASY)
        .build()))
    .pageable(all(Sort.by(Direction.ASC, "books.pageCount")))
    .build());
```

### Aggregating data

You can aggregate data and collect their count. Aggregation groups data by a provided entity field and collect the result
in a `Map` where a key stands unique found value for the entity field and
the value of the map stores a number of its appearances.

```java
String aggregatorName = "aggregator-genre";
String field = "genre";
SearchResult<Book> result = searchEngineForBooks.search(Query.<Book>builder()
    .clazz(Book.class)
    .aggregators(List.of(Aggregator.of(aggregatorName, field)))
    .build());
```
