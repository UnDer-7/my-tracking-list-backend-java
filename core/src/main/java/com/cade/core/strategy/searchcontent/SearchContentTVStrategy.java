package com.cade.core.strategy.searchcontent;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import io.smallrye.mutiny.Multi;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor
public class SearchContentTVStrategy implements SearchContentStrategy {

    @Override
    public Multi<Content> execute(final String searchString) {
        return Multi.createFrom().item(Content.builder().titleName("Sopranos | %s".formatted(searchString)).releasedDate(LocalDate.now().minusYears(20)).build());
    }

    @Override
    public boolean accept(final ContentType contentType) {
        return ContentType.TV == contentType;
    }

}
