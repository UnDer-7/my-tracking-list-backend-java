package com.cade.backend.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @see <a href="https://quarkus.io/guides/rest-client#custom-headers-support">
 *     https://quarkus.io/guides/rest-client#custom-headers-support
 * </a>
 */
@Slf4j
@ApplicationScoped
@RequiredArgsConstructor(onConstructor_={@Inject})
public class TheMovieDBClientHeaders implements ClientHeadersFactory {

    @ConfigProperty(name = "MY_TRACKING_LIST_BACKEND_THE_MOVIE_DB_TOKEN")
    private String theMovieDBToken;

    @Override
    public MultivaluedMap<String, String> update(
        final MultivaluedMap<String, String> incomingHeaders,
        final MultivaluedMap<String, String> clientOutgoingHeaders) {

        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        result.add(HttpHeaders.AUTHORIZATION, "Bearer " + theMovieDBToken);

        return result;
    }
}
