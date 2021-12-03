package com.cade.backend.adapter.rest;

import com.cade.api.v1.user.ResponseUserDTO;
import com.cade.api.v1.user.UserRestService;
import com.cade.backend.adapter.mapper.UserMapper;
import com.cade.core.ports.driver.UserService;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Authenticated
@Path("/users")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor_={@Inject})
public class UserRestServiceImpl implements UserRestService {
    private final UserService service;
    private final UserMapper mapper;

    @GET
    @Override
    @Path("/email/{email}")
    public Uni<ResponseUserDTO> findByEmail(@PathParam("email") final String email) {
        return service.findByEmail(email).map(mapper::toResponseUserDTO);
    }
}
