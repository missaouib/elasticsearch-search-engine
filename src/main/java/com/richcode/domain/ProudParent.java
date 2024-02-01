package com.richcode.domain;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

import java.util.Set;

@Getter
@Setter
@Table(name = "proud_parents")
@Indexed
public class ProudParent {

    @Id
    @DocumentId
    private Long id;

    @KeywordField(normalizer = "lowercase", sortable = Sortable.YES, aggregable = Aggregable.YES)
    private String name;

    @IndexedEmbedded(includePaths = {"start", "end"}, structure = ObjectStructure.NESTED)
    Set<DateInterval> parentalLeaves;

}
