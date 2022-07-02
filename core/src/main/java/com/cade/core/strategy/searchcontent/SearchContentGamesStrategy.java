package com.cade.core.strategy.searchcontent;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import io.smallrye.mutiny.Multi;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor
public class SearchContentGamesStrategy implements SearchContentStrategy {

    @Override
    public Multi<Content> execute(final String searchString) {
        return Multi.createFrom().item(Content.builder().titleName("Yakuza 2 | %s".formatted(searchString)).releasedDate(LocalDate.now().minusYears(10)).build());
    }

    @Override
    public boolean accept(final ContentType contentType) {
        return ContentType.GAME == contentType;
    }

}
