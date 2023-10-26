package com.richcode.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.engine.backend.types.ObjectStructure;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "books")
@Entity
@Indexed
public class Author {

    @Id
    @DocumentId
    private Long id;

    @KeywordField(normalizer = "lowercase", sortable = Sortable.YES, aggregable = Aggregable.YES)
    private String name;

    @ManyToMany
    @IndexedEmbedded(includeDepth = 1, structure = ObjectStructure.NESTED)
    private List<Book> books = new ArrayList<>();

}
