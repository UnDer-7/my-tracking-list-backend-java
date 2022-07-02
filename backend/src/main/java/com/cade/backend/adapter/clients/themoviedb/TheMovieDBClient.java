package com.cade.backend.adapter.clients.themoviedb;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class TheMovieDBClient {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public TheMovieDBClient(final Vertx vertx, final ObjectMapper objectMapper) {
        this.webClient = WebClient.create(vertx);
        this.objectMapper = objectMapper;
    }

    
}
