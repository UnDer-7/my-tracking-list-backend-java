package com.cade.backend.adapter.clients.google;

import com.cade.backend.config.ServerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
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
    private final ServerConfig serverConfig;

    public OAuthGoogleClient(final Vertx vertx, final ObjectMapper objectMapper, final ServerConfig serverConfig) {
        this.client = WebClient.create(vertx);
        this.objectMapper = objectMapper;
        this.serverConfig = serverConfig;
    }

    public Uni<GoogleOAuthResponseDTO> getAccessToken(final String authCode) {
        final var googleSetting = serverConfig.oauth().google();

        return client.postAbs(googleSetting.serverUrl() + "/token")
            .putHeader(HttpHeaders.CONTENT_LENGTH, "0")
            .addQueryParam("code", authCode)
            .addQueryParam("client_id", googleSetting.clientId())
            .addQueryParam("client_secret", googleSetting.clientSecret())
            .addQueryParam("redirect_uri", googleSetting.redirectUri())
            .addQueryParam("grant_type", googleSetting.grantType())
            .send()
            .onItem()
            .transform(Unchecked.function(res -> {
                final var json = res.bodyAsString();
                if (res.statusCode() == RestResponse.StatusCode.OK) {
                    return objectMapper.readValue(json, GoogleOAuthResponseDTO.class);
                }
                log.warn("Falha ao se comunicar com google. Res: {} | StatusCode: {}", json, res.statusCode());
                throw new RuntimeException("Falha ao se comunicar com google"); // todo: Trocar exceptin
            }));
    }

}
