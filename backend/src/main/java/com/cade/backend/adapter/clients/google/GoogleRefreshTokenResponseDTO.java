package com.cade.backend.adapter.clients.google;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleRefreshTokenResponseDTO(
    @JsonProperty("access_token") String accessToken,
    @JsonProperty("expires_in") Long expiresIn,
    String scope,
    @JsonProperty("token_type") String tokenType,
    @JsonProperty("id_token") String idToken
) {

}
