package com.cade.api.dto;

import lombok.Builder;

@Builder
public record TokenDTO(
    String tokenEncoded,
    String refreshTokenEncoded
) {

}
