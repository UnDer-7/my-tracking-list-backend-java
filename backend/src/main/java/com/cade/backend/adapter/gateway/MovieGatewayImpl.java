package com.cade.backend.adapter.gateway;

import com.cade.backend.adapter.mapper.ContentMapper;
import com.cade.backend.clients.themoviedb.TheMovieDBClient;
import com.cade.backend.config.ServerConfig;
import com.cade.core.domain.Content;
import com.cade.core.domain.Page;
import com.cade.core.ports.driven.MovieGateway;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class MovieGatewayImpl implements MovieGateway {
    private final TheMovieDBClient theMovieDBClient;
    private final ContentMapper contentMapper;
    private final ServerConfig serverConfig;

    @Override
    public Uni<Page<Content>> search(final Integer page, final String searchArgs) {
        return theMovieDBClient.searchMovies(page, searchArgs)
            .map(contentMapper::toDomain)
            .map(contentPage -> {
                contentPage.getResults()
                    .forEach(content -> {
                        content.concatPosterPathUrl(serverConfig.client().theMovieDb().imageUrl());
                        content.concatBackdropPathUrl(serverConfig.client().theMovieDb().imageUrl());
                    });
                return contentPage;
            });
    }

}
