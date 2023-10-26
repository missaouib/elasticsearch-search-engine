package com.richcode.support;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityFieldPathBuilder {

    private static final String SEPARATOR = ".";

    private final List<String> fields = new LinkedList<>();

    public static EntityFieldPathBuilder instance() {
        return new EntityFieldPathBuilder();
    }

    public EntityFieldPathBuilder add(final String field) {
        fields.add(field);
        return this;
    }

    public String build() {
        return String.join(SEPARATOR, fields);
    }

}
