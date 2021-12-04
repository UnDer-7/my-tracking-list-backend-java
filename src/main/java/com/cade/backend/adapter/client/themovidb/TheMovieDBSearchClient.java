package com.cade.backend.adapter.client.themovidb;

import com.cade.backend.adapter.client.themovidb.dto.ResponsePage;
import com.cade.backend.adapter.client.themovidb.dto.ResponseSearch;
import com.cade.backend.config.TheMovieDBClientHeaders;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/search")
@RegisterClientHeaders(TheMovieDBClientHeaders.class)
@RegisterRestClient(configKey = "the-movie-db-client")
public interface TheMovieDBSearchClient {
    @GET
    @Path("/tv")
    Multi<ResponsePage<ResponseSearch>> searchTV(@QueryParam("query") String query, @QueryParam("page") Long pageIndex, @QueryParam("language") String language);
}
