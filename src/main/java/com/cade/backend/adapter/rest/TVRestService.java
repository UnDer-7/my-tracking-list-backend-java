package com.cade.backend.adapter.rest;

import com.cade.api.v1.shared.PageDTO;
import com.cade.api.v1.shared.QueryDTO;
import com.cade.api.v1.tv.ResponseTVSearchDTO;
import com.cade.api.v1.tv.TVResource;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tv")
@Authenticated
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor_={@Inject})
public class TVRestService implements TVResource {

    @GET
    @Path("/search")
    @Override
    public Uni<PageDTO<ResponseTVSearchDTO>> search(@BeanParam QueryDTO queryDTO) {
        return null;
    }
}
