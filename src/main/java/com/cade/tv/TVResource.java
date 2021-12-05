package com.cade.tv;

import com.cade.utils.dtos.PageDTO;
import com.cade.utils.dtos.QueryDTO;
import com.cade.tv.dto.ResponseTVSearchDTO;
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
public class TVResource {

    @GET
    @Path("/search")
    public Uni<PageDTO<ResponseTVSearchDTO>> search(@BeanParam QueryDTO queryDTO) {
        return null;
    }
}
