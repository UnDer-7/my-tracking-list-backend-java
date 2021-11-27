package com.cade.backend.adapter.rest;

import com.cade.api.v1.user.ResponseUserDTO;
import com.cade.api.v1.user.UserRestService;
import com.cade.backend.adapter.mapper.UserMapper;
import com.cade.core.ports.driver.UserService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/users")
@ApplicationScoped
@RequiredArgsConstructor
public class UserRestServiceImpl implements UserRestService {
    private final UserService service;
    private final UserMapper mapper;

    @GET
    @Override
    public Uni<ResponseUserDTO> findByEmail(final String email) {
        return service.findByEmail(email).map(mapper::toResponseUserDTO);
    }
}
