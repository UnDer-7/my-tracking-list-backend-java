package com.cade.api.controller;

import com.cade.api.dto.UserDTO;
import io.smallrye.mutiny.Uni;

public interface UserController {
    Uni<UserDTO> getUser();
}
