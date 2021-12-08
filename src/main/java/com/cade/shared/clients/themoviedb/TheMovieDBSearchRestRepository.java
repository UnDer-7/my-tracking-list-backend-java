package com.cade.shared.clients.themoviedb;

import com.cade.exceptions.InternalServerErrorException;
import com.cade.shared.clients.themoviedb.dto.ResponsePageDTO;
import com.cade.shared.clients.themoviedb.dto.ResponseSearchDTO;
import com.cade.features.tv.TVMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Slf4j
@ApplicationScoped
public class TheMovieDBSearchRestRepository {
    private final String theMovieDBToken;
    private final String theMovieDBHost;

    private final WebClient client;
    private final TVMapper tvMapper;

    @Inject
    public TheMovieDBSearchRestRepository(
        final Vertx vertx,
        final TVMapper tvMapper,
        @ConfigProperty(name = "MY_TRACKING_LIST_BACKEND_THE_MOVIE_DB_TOKEN") final String theMovieDBToken,
        @ConfigProperty(name = "MY_TRACKING_LIST_BACKEND_THE_MOVIE_DB_HOST") final String theMovieDBHost) {

        this.theMovieDBHost = theMovieDBHost;
        this.theMovieDBToken = theMovieDBToken;
        this.tvMapper = tvMapper;
        this.client = WebClient.create(vertx);
    }

    public Uni<ResponsePageDTO<ResponseSearchDTO>> searchTV(final String query, final Long pageIndex, final String language) {
        return client
            .get(theMovieDBHost, "/3/search/tv")
            .addQueryParam("query", query)
            .addQueryParam("page", pageIndex.toString())
            .addQueryParam("language", language)
            .putHeader(HttpHeaders.AUTHORIZATION, "Bearer " + theMovieDBToken)
            .putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .send()
            .flatMap(this::handleResponse);
    }

    private Uni<ResponsePageDTO<ResponseSearchDTO>> handleResponse(final HttpResponse<Buffer> res) {
        if (res.statusCode() == 200) {
            return Uni.createFrom().item(() -> unmarshallResponse(res.bodyAsString()));
        } else {
            log.warn(
                "TheMovieDB request fail | StatusCode: {} | ResponseBody: {}",
                res.statusCode(),
                res.bodyAsString());
            throw new InternalServerErrorException(new RuntimeException("TheMovieDB request fail"));
        }
    }

    private ResponsePageDTO<ResponseSearchDTO> unmarshallResponse(final String json) {
        try {
            return new ObjectMapper().readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Fail to decode TheMovieDB request body");
            throw new InternalServerErrorException(e);
        }
    }

}
