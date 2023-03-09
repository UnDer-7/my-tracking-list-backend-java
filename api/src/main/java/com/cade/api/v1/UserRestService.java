package com.cade.api.v1;

import com.cade.api.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = UserRestService.PATH)
public interface UserRestService {

    String PATH = "/users";

    @PostMapping()
    UserDTO create(@RequestBody UserDTO user);
}
