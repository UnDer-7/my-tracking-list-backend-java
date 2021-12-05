package com.cade.tv.repository;

import com.cade.infrastructure.headers.TheMovieDBClientHeaders;
import com.cade.tv.dto.ResponsePageDTO;
import com.cade.tv.dto.ResponseSearchDTO;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/search")
@RegisterClientHeaders(TheMovieDBClientHeaders.class)
@RegisterRestClient(configKey = "the-movie-db-client")
public interface TheMovieDBSearchRestRepository {
    @GET
    @Path("/tv")
    Multi<ResponsePageDTO<ResponseSearchDTO>> searchTV(@QueryParam("query") String query, @QueryParam("page") Long pageIndex, @QueryParam("language") String language);
}
