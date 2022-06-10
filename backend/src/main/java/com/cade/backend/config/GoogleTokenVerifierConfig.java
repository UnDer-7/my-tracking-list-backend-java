package com.cade.backend.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Collections;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class GoogleTokenVerifierConfig {
    private final ServerConfig serverConfig;

    @Produces
    public GoogleIdTokenVerifier googleIdTokenVerifier() {
        log.debug("Creating bean GoogleIdTokenVerifier");
        return new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
            .setAudience(Collections.singletonList(serverConfig.oauth().google().clientId()))
            .build();
    }
}
