package com.cade.features.user.auth;

import com.cade.features.user.ResponseUserDTO;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.NoCache;

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
    @NoCache
    @Path("/register")
    public Uni<ResponseUserDTO> register() {
        return Uni.createFrom().item(ResponseUserDTO.builder().email("register").build());
    }

    @POST
    @Authenticated
    @Path("/sign-in")
    public Uni<ResponseUserDTO> signIn() {
        return Uni.createFrom().item(ResponseUserDTO.builder().email("signIn").build());
    }
}
