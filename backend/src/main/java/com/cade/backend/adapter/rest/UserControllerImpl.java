package com.cade.backend.adapter.rest;

import com.cade.api.controller.UserController;
import com.cade.api.dto.UserDTO;
import com.cade.backend.adapter.mapper.UserMapper;
import com.cade.core.ports.driver.UserService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class UserControllerImpl implements UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @GET
    public UserDTO getUsr() {
        var usr = userService.getUser();
        return userMapper.toDTO(usr);
    }
}
