package com.richcode.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Indexed
public class Book {

    @Id
    private Long id;

    @NaturalId
    @DocumentId
    @KeywordField(sortable = Sortable.YES)
    private String isbn;

    @KeywordField(normalizer = "lowercase", sortable = Sortable.YES, aggregable = Aggregable.YES)
    private String title;

    @Enumerated(EnumType.STRING)
    @KeywordField(aggregable = Aggregable.YES)
    private BookGenre genre;

    @GenericField(sortable = Sortable.YES)
    private OffsetDateTime published;

    @GenericField(sortable = Sortable.YES)
    private Long pageCount;

    @CreatedDate
    @GenericField(sortable = Sortable.YES)
    private OffsetDateTime createdAt;

    @ManyToMany(mappedBy = "books")
    @IndexedEmbedded(includeDepth = 1)
    private List<Author> authors = new ArrayList<>();

}
