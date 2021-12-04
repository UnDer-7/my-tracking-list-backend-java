package com.cade.backend.adapter.rest;

import com.cade.api.v1.auth.AuthRestService;
import com.cade.api.v1.auth.RequestRegisterDTO;
import com.cade.api.v1.user.ResponseUserDTO;
import com.cade.backend.adapter.mapper.AuthMapper;
import com.cade.core.ports.driver.AuthService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
public class AuthRestServiceImpl implements AuthRestService {
    private final AuthService authService;
    private final AuthMapper authMapper;

    @POST
    @Override
    @Path("/register")
    public Uni<ResponseUserDTO> register(final RequestRegisterDTO requestRegisterDTO) {
        return authService
            .register(requestRegisterDTO.getToken())
            .map(authMapper::toResponseUserDTO);
    }

    @POST
    @Override
    @Path("/sign-in")
    public Uni<ResponseUserDTO> signIn(final RequestRegisterDTO requestRegisterDTO) {
        return authService
            .signIn(requestRegisterDTO.getToken())
            .map(authMapper::toResponseUserDTO);
    }
}
