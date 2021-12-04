package com.cade.backend.adapter.client.themovidb;

import com.cade.backend.config.TheMovieDBClientHeaders;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/tv")
@RegisterClientHeaders(TheMovieDBClientHeaders.class)
@RegisterRestClient(configKey = "the-movie-db-client")
public interface TheMovieDBTVClient {

    @GET
    @Path("/{id}")
    Uni<Object> details(@PathParam("id") Long id);
}
