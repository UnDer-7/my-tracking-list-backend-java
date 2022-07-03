package com.cade.backend.adapter.gateway;

import com.cade.backend.adapter.mapper.ContentMapper;
import com.cade.backend.clients.themoviedb.TheMovieDBClient;
import com.cade.core.domain.Content;
import com.cade.core.domain.Page;
import com.cade.core.ports.driven.TVGateway;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class TVGatewayImpl implements TVGateway {

    private final TheMovieDBClient theMovieDBClient;
    private final ContentMapper contentMapper;

    @Override
    public Uni<Page<Content>> search(final Integer page, final String searchArgs) {
        return theMovieDBClient.searchTVShows(page, searchArgs)
            .map(contentMapper::toDomain);
    }

}
