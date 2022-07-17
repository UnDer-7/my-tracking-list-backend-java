package com.cade.backend.clients.rawg;

import com.cade.backend.clients.AbstractWebClient;
import com.cade.backend.config.ServerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.mutiny.core.Vertx;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@NoArgsConstructor
public class RawgClient extends AbstractWebClient {

    private ServerConfig.Client.ClientSetting rawgSetting;

    public RawgClient(final Vertx vertx, final ObjectMapper objectMapper, final ServerConfig serverConfig) {
        super(vertx, objectMapper);
        this.rawgSetting = serverConfig.client().rawg();
    }


}
