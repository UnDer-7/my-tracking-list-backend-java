package com.cade.backend.adapter.gateway;

import com.cade.core.domain.Content;
import com.cade.core.domain.Page;
import com.cade.core.ports.driven.GamesGateway;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class GamesGatewayImpl implements GamesGateway {

    @Override
    public Uni<Page<Content>> search(final Integer page, final String searchArgs) {
        return null;
    }

}
