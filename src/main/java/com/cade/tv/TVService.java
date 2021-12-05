package com.cade.tv;

import com.cade.tv.repository.TheMovieDBSearchRestRepository;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class TVService {
    private final TheMovieDBSearchRestRepository restRepository;

    @Inject
    public TVService(
        @RestClient final TheMovieDBSearchRestRepository restRepository) {
        this.restRepository = restRepository;
    }
}
