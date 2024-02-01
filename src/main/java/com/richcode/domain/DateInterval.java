package com.richcode.domain;

import jakarta.persistence.Embeddable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import java.time.LocalDate;

@Embeddable
public class DateInterval {

    @GenericField
    LocalDate start;

    @GenericField
    LocalDate end;
}