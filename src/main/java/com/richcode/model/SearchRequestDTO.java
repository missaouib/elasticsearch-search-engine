package com.richcode.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchRequestDTO {

    private String text;

    private List<String> fields = new ArrayList<>();
    private int limit;
}