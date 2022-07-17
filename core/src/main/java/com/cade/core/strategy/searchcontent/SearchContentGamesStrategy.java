package com.cade.core.strategy.searchcontent;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import com.cade.core.domain.Page;
import com.cade.core.ports.driven.GamesGateway;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor
public class SearchContentGamesStrategy implements SearchContentStrategy {

    private final GamesGateway gamesGateway;

    @Override
    public Uni<Page<Content>> execute(Integer page, String searchString) {
        return gamesGateway.search(page, searchString);
    }

    @Override
    public boolean accept(final ContentType contentType) {
        return ContentType.GAME == contentType;
    }

}
