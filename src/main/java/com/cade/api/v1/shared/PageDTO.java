package com.cade.api.v1.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private Long pageIndex;
    private List<T> results;
    private Long totalPages;
    private Long totalResults;
}
