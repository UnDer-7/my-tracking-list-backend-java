package com.cade.backend.adapter.mapper;

import com.cade.api.dto.UserDTO;
import com.cade.core.domain.UserDomain;
import com.cade.core.utils.Constants;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface UserMapper {

    UserDTO toDTO(UserDomain domain);

}
