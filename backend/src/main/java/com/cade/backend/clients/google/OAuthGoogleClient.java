package com.cade.backend.clients.google;

import com.cade.backend.clients.AbstractWebClient;
import com.cade.backend.config.ServerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;

// https://developers.google.com/identity/protocols/oauth2/web-server?hl=en#exchange-authorization-code
@Slf4j
@ApplicationScoped
@NoArgsConstructor
public class OAuthGoogleClient extends AbstractWebClient {

    private ServerConfig.Oauth.OauthSetting googleSetting;

    @Inject
    public OAuthGoogleClient(final Vertx vertx, final ObjectMapper objectMapper, final ServerConfig serverConfig) {
        super(vertx, objectMapper);
        this.googleSetting = serverConfig.oauth().google();
    }

    // todo: Cache
    public Uni<GoogleOAuthResponseDTO> getAccessToken(final String authCode) {
        return webClient.postAbs(googleSetting.serverUrl() + "/token")
            .putHeader(HttpHeaders.CONTENT_LENGTH, "0")
            .addQueryParam("code", authCode)
            .addQueryParam("client_id", googleSetting.clientId())
            .addQueryParam("client_secret", googleSetting.clientSecret())
            .addQueryParam("redirect_uri", googleSetting.redirectUri())
            .addQueryParam("grant_type", "authorization_code")
            .send()
            .map(handleResponse(GoogleOAuthResponseDTO.class));
    }

    public Uni<GoogleRefreshTokenResponseDTO> refreshToken(final String refreshToken) {
        return webClient.postAbs(googleSetting.serverUrl() + "/token")
            .putHeader(HttpHeaders.CONTENT_LENGTH, "0")
            .addQueryParam("refresh_token", refreshToken)
            .addQueryParam("client_id", googleSetting.clientId())
            .addQueryParam("client_secret", googleSetting.clientSecret())
            .addQueryParam("grant_type", "refresh_token")
            .send()
            .map(handleResponse(GoogleRefreshTokenResponseDTO.class));
    }

}
