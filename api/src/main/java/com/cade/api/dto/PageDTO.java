package com.cade.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private Long page;
    private List<T> results;
    private Long totalResults;
    private Long totalPages;
    private Boolean isTheLastPage;
}
