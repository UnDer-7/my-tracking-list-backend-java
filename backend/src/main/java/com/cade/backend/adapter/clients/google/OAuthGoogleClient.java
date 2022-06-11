package com.cade.backend.adapter.clients.google;

import com.cade.backend.config.ServerConfig;
import com.cade.core.domain.ErrorMessages;
import com.cade.core.exception.InternalServerErrorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.HttpHeaders;

// https://developers.google.com/identity/protocols/oauth2/web-server?hl=en#exchange-authorization-code
@Slf4j
@ApplicationScoped
public class OAuthGoogleClient {

    private final WebClient client;
    private final ObjectMapper objectMapper;
    private final ServerConfig.Oauth.OauthSetting googleSetting;

    public OAuthGoogleClient(final Vertx vertx, final ObjectMapper objectMapper, final ServerConfig serverConfig) {
        this.client = WebClient.create(vertx);
        this.objectMapper = objectMapper;
        this.googleSetting = serverConfig.oauth().google();
    }

    // todo: Cache
    public Uni<GoogleOAuthResponseDTO> getAccessToken(final String authCode) {
        return client.postAbs(googleSetting.serverUrl() + "/token")
            .putHeader(HttpHeaders.CONTENT_LENGTH, "0")
            .addQueryParam("code", authCode)
            .addQueryParam("client_id", googleSetting.clientId())
            .addQueryParam("client_secret", googleSetting.clientSecret())
            .addQueryParam("redirect_uri", googleSetting.redirectUri())
            .addQueryParam("grant_type", "authorization_code")
            .send()
            .map(res -> {
                final var json = res.bodyAsString();
                if (res.statusCode() == RestResponse.StatusCode.OK) {
                    return deserialize(json, GoogleOAuthResponseDTO.class);
                }
                log.warn("Falha ao se comunicar com Google(AccessToken). Res: {} | StatusCode: {}", json, res.statusCode());
                throw new RuntimeException("Falha ao se comunicar com Google(AccessToken)."); // todo: Trocar exceptin
            });
    }

    public Uni<GoogleRefreshTokenResponseDTO> refreshToken(final String refreshToken) {
        return client.postAbs(googleSetting.serverUrl() + "/token")
            .putHeader(HttpHeaders.CONTENT_LENGTH, "0")
            .addQueryParam("refresh_token", refreshToken)
            .addQueryParam("client_id", googleSetting.clientId())
            .addQueryParam("client_secret", googleSetting.clientSecret())
            .addQueryParam("grant_type", "refresh_token")
            .send()
            .map(res -> {
                final var json = res.bodyAsString();
                if (res.statusCode() == RestResponse.StatusCode.OK) {
                    return deserialize(json, GoogleRefreshTokenResponseDTO.class);
                }
                log.warn("Falha ao se comunicar com Google (refresh token). Res: {} | StatusCode: {}", json, res.statusCode());
                throw new RuntimeException("Falha ao se comunicar com Google (refresh token)"); // todo: Trocar exception
            });
    }

    private <T> T deserialize(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException(
                ErrorMessages.SERIALIZATION_ERROR
                    .setCustomMsg("Class with problem: %s - JSON: %s"
                        .formatted(clazz.getName(), json))
            );
        }
    }

}
