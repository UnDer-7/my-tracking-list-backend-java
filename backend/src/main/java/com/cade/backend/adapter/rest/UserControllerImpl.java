package com.cade.backend.adapter.rest;

import com.cade.api.controller.UserController;
import com.cade.api.dto.UserDTO;
import com.cade.backend.adapter.mapper.UserMapper;
import com.cade.core.ports.driver.UserService;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Slf4j
@Authenticated
@Path("/users")
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserControllerImpl implements UserController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final SecurityContext securityContext;

    @GET
    @Override
    public Uni<UserDTO> getUser() {
        return userService.getUserByEmail(securityContext.getUserPrincipal().getName())
            .map(userMapper::toDTO);
    }

}
