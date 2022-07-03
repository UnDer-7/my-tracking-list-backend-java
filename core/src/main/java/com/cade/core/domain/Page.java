package com.cade.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page<T> {
    private Long page;
    private List<T> results;
    private Long totalResults;
    private Long totalPages;
}
