package com.cade.backend.adapter.rest;

import com.cade.api.v1.auth.AuthRestService;
import com.cade.api.v1.auth.SignUpDTO;
import com.cade.api.v1.user.ResponseUserDTO;
import com.cade.backend.adapter.mapper.AuthMapper;
import com.cade.core.ports.driver.AuthService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

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
public class AuthRestServiceImpl implements AuthRestService {
    private final AuthService authService;
    private final AuthMapper authMapper;

    @POST
    @Override
    public Uni<ResponseUserDTO> signUp(final SignUpDTO signUp) {
        return authService
            .singUp(authMapper.toAuth(signUp))
            .map(authMapper::toResponseUserDTO);
    }
}
