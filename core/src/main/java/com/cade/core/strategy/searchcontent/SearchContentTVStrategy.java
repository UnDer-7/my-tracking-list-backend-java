package com.cade.core.strategy.searchcontent;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import com.cade.core.domain.Page;
import com.cade.core.ports.driven.TVGateway;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class SearchContentTVStrategy implements SearchContentStrategy {

    private final TVGateway tvGateway;

    @Override
    public Uni<Page<Content>> execute(Integer page, String searchString) {
        return tvGateway.search(page, searchString);
    }

    @Override
    public boolean accept(final ContentType contentType) {
        return ContentType.TV == contentType;
    }

}
