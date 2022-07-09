package com.cade.core.strategy.searchcontent;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import com.cade.core.domain.Page;
import com.cade.core.ports.driven.MovieGateway;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class SearchContentMovieStrategy implements SearchContentStrategy {

    private final MovieGateway movieGateway;

    @Override
    public Uni<Page<Content>> execute(Integer page, String searchString) {
        return movieGateway.search(page, searchString);
    }

    @Override
    public boolean accept(final ContentType contentType) {
        return ContentType.MOVIE == contentType;
    }

}
