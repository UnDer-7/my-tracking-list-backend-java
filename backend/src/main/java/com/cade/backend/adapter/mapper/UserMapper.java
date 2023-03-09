package com.cade.backend.adapter.mapper;

import com.cade.api.dto.UserDTO;
import com.cade.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User entity);

    User toEntity(UserDTO dto);
}
