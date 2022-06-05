package com.cade.backend.adapter.mapper;

import com.cade.api.dto.UserDTO;
import com.cade.core.domain.UserDomain;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {
    public UserDTO toDTO(UserDomain domain) {
        var dto = new UserDTO();
        dto.setEmail(domain.email);

        return dto;
    }
}
