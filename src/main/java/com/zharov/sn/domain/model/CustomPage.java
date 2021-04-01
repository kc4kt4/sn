package com.zharov.sn.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomPage<T> {
    private List<T> content;
    private long totalElements;
    private int page;
    private int totalPages;
}
