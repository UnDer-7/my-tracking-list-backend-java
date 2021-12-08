package com.cade.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.QueryParam;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryDTO {
    @QueryParam("query")
    private String query;

    @QueryParam("pageIndex")
    private Long pageIndex;
}
