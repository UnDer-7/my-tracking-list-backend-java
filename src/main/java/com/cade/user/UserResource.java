package com.cade.user;

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
class UserResource {
    private final UserService service;
    private final UserMapper mapper;

    @GET
    @Path("/email/{email}")
    public Uni<ResponseUserDTO> findByEmail(@PathParam("email") final String email) {
        return service.findByEmail(email).map(mapper::toResponseUserDTO);
    }
}
