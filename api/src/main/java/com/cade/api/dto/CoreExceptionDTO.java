package com.cade.api.dto;

import lombok.Builder;

@Builder
public record CoreExceptionDTO(
    String errCode,
    String userMsg,
    String customMsg,
    int statusCode,
    String timestamp,
    String errUniqueIdentifier
) {

}
