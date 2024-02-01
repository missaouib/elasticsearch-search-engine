package com.richcode.domain;

import com.richcode.model.PlantRoutingBinder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.RoutingBinderRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import jakarta.persistence.*;
import java.time.Instant;

@Indexed(routingBinder = @RoutingBinderRef(type = PlantRoutingBinder.class))
@Entity
@Table(name = "plants")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Plant {

    public Plant() {
        this.createdAt = Instant.now();
    }

    public Plant(String name, String scientificName, String family) {
        this.name = name;
        this.scientificName = scientificName;
        this.family = family;
        this.createdAt = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FullTextField()
    @NaturalId()
    private String name;

    @FullTextField()
    @NaturalId()
    private String scientificName;

    @FullTextField()
    private String family;

    private Instant createdAt ;
}