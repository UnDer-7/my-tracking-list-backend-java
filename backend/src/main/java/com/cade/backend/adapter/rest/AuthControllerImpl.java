package com.cade.backend.adapter.rest;

import com.cade.api.controller.AuthController;
import com.cade.api.dto.TokenDTO;
import com.cade.backend.adapter.mapper.TokenMapper;
import com.cade.core.ports.driver.AuthService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.RestHeader;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final TokenMapper tokenMapper;

    @POST
    @Path("/google/sign-in")
    @Override
    public Uni<TokenDTO> signIn(@RestHeader("Auth-code") final String authCode) {
        return authService.signIn(authCode).map(tokenMapper::toDTO);
    }

    @POST
    @Path("/google/register")
    @Override
    public Uni<TokenDTO> register(@RestHeader("Auth-code") final String authCode) {
        return authService.register(authCode).map(tokenMapper::toDTO);
    }

    @POST
    @Path("/google/refresh")
    @Override
    public Uni<TokenDTO> refreshToken(@RestHeader("Refresh-token") final String refreshToken) {
        return authService.refreshToken(refreshToken).map(tokenMapper::toDTO);
    }

}
