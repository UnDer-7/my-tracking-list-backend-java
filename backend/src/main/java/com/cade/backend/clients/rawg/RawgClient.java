package com.cade.backend.clients.rawg;

import com.cade.backend.clients.AbstractWebClient;
import com.cade.backend.clients.rawg.dto.PageDTO;
import com.cade.backend.clients.rawg.dto.SearchResultDTO;
import com.cade.backend.config.ServerConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
@NoArgsConstructor
public class RawgClient extends AbstractWebClient {

    private ServerConfig.Client.ClientSetting rawgSetting;

    @Inject
    public RawgClient(final Vertx vertx, final ObjectMapper objectMapper, final ServerConfig serverConfig) {
        super(vertx, objectMapper);
        this.rawgSetting = serverConfig.client().rawg();
    }

    public Uni<PageDTO<SearchResultDTO>> searchByName(final Integer page, final String query) {
        return webClient.getAbs(rawgSetting.serverUrl() + "/games")
            .addQueryParam("key", rawgSetting.apiKey())
            .addQueryParam("search", query)
            .addQueryParam("page_size", "20")
            .addQueryParam("page", page.toString())
            .send()
            .map(handleResponse(new TypeReference<PageDTO<SearchResultDTO>>() {}));

    }
}
