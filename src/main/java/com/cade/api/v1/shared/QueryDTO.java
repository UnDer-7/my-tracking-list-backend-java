package com.cade.api.v1.shared;

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
    private Long query;

    @QueryParam("pageIndex")
    private Long pageIndex;
}
