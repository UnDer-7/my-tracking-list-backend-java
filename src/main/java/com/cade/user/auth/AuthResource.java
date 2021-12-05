package com.cade.user.auth;

import com.cade.user.ResponseUserDTO;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/auth")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor_={@Inject})
public class AuthResource {
    private final AuthService authService;
    private final AuthMapper authMapper;

    @POST
    @Path("/register")
    public Uni<ResponseUserDTO> register(@Valid final RequestRegisterDTO requestRegisterDTO) {
        return authService
            .register(requestRegisterDTO.getToken())
            .map(authMapper::toResponseUserDTO);
    }

    @POST
    @Path("/sign-in")
    public Uni<ResponseUserDTO> signIn(@Valid final RequestRegisterDTO requestRegisterDTO) {
        return authService
            .signIn(requestRegisterDTO.getToken())
            .map(authMapper::toResponseUserDTO);
    }
}
