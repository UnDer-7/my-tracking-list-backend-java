package com.cade.shared.clients.themoviedb;

import com.cade.infrastructure.headers.TheMovieDBClientHeaders;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/tv")
@RegisterClientHeaders(TheMovieDBClientHeaders.class)
@RegisterRestClient(configKey = "the-movie-db-client")
public interface TheMovieDBTVRestRepository {

    @GET
    @Path("/{id}")
    Uni<Object> details(@PathParam("id") Long id);
}
