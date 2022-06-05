package com.cade.api.controller;

import com.cade.api.dto.UserDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;

public interface UserController {
    UserDTO getUsr();
}
