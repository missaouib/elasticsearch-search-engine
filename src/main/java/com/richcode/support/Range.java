package com.richcode.support;

public record Range<T>(T from, T to) {

    public static <T> Range<T> range(T from, T to) {
        return new Range<>(from, to);
    }

    public static <T> Range<T> from(T from) {
        return new Range<>(from, null);
    }

    public static <T> Range<T> to(T to) {
        return new Range<>(null, to);
    }

    public boolean isEmpty() {
        return from == null && to == null;
    }

}
