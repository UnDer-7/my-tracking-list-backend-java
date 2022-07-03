package com.cade.backend.clients.themoviedb;

import com.cade.backend.clients.AbstractWebClient;
import com.cade.backend.clients.themoviedb.dto.PageDTO;
import com.cade.backend.clients.themoviedb.dto.SearchResultDTO;
import com.cade.backend.config.ServerConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;

@Slf4j
@ApplicationScoped
@NoArgsConstructor
public class TheMovieDBClient extends AbstractWebClient {

    private ServerConfig.Client.ClientSetting theMovieDbSetting;

    @Inject
    public TheMovieDBClient(final Vertx vertx, final ObjectMapper objectMapper, final ServerConfig serverConfig) {
        super(vertx, objectMapper);
        this.theMovieDbSetting = serverConfig.client().theMovieDb();
    }

    public Uni<PageDTO<SearchResultDTO>> searchTVShows(final Integer page, final String query) {
        return webClient.getAbs(theMovieDbSetting.serverUrl() + "/search/tv")
            .putHeader(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(theMovieDbSetting.apiKey()))
            .addQueryParam("language", "en-US")
            .addQueryParam("page", page.toString())
            .addQueryParam("query", query)
            .addQueryParam("include_adult", Boolean.FALSE.toString())
            .send()
            .map(handleResponse(new TypeReference<PageDTO<SearchResultDTO>>() {
            }));
    }

    public Uni<PageDTO<SearchResultDTO>> searchMovies(final Integer page, final String query) {
        return webClient.getAbs(theMovieDbSetting.serverUrl() + "/search/movie")
            .putHeader(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(theMovieDbSetting.apiKey()))
            .addQueryParam("language", "en-US")
            .addQueryParam("page", page.toString())
            .addQueryParam("query", query)
            .addQueryParam("include_adult", Boolean.FALSE.toString())
            .send()
            .map(handleResponse(new TypeReference<PageDTO<SearchResultDTO>>() {
            }));
    }
}
