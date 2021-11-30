package com.cade.backend.adapter.rest;

import com.cade.api.v1.auth.SignUpDTO;
import com.cade.api.v1.user.ResponseUserDTO;
import com.cade.backend.adapter.mapper.AuthMapper;
import com.cade.core.ports.driver.AuthService;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/auth")
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthRestServiceImpl  {
    private final AuthService authService;
    private final AuthMapper authMapper;

    @GET
    @Authenticated
    @Path("/block")
    public Uni<SignUpDTO> blocked() {
        return Uni.createFrom().item(SignUpDTO.builder().token("block").build());
    }

    @GET
    @Path("/allow")
    public Uni<SignUpDTO> allow() {
        return Uni.createFrom().item(SignUpDTO.builder().token("allow").build());
    }

    @POST
    @SneakyThrows
    public Uni<ResponseUserDTO> signUp(final SignUpDTO signUp) {
        return Uni.createFrom().nullItem();
    }
}
