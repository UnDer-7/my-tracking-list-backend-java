package com.cade.core.service;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import com.cade.core.domain.ErrorMessages;
import com.cade.core.domain.Page;
import com.cade.core.exception.InternalServerErrorException;
import com.cade.core.ports.driver.SearchContentService;
import com.cade.core.strategy.searchcontent.SearchContentStrategy;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;

@ApplicationScoped
@RequiredArgsConstructor
public class SearchContentServiceImpl implements SearchContentService {

    private final Instance<SearchContentStrategy> searchContentStrategies;

    @Override
    public Uni<Page<Content>> searchByContent(final ContentType contentType, final Integer page, final String searchArgs) {
        return searchContentStrategies.stream()
            .filter(strategy -> strategy.accept(contentType))
            .findFirst()
            .orElseThrow(() -> new InternalServerErrorException(ErrorMessages.SEARCH_CONTENT_STRATEGY_NOT_FOUND.setCustomMsg(
                "ContentType used: %s".formatted(contentType))))
            .execute(page, searchArgs);
    }

}
