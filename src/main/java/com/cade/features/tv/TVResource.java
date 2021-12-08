package com.cade.features.tv;

import com.cade.shared.dtos.PageDTO;
import com.cade.shared.dtos.QueryDTO;
import com.cade.features.tv.dto.ResponseTVSearchDTO;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/tv")
@Authenticated
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor_={@Inject})
public class TVResource {

    private final TVService tvService;

    @GET
    @Path("/search")
    public Uni<PageDTO<ResponseTVSearchDTO>> search(@BeanParam QueryDTO queryDTO) {
        return tvService.search(queryDTO);
    }
}
