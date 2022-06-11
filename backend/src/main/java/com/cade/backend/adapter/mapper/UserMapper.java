package com.cade.backend.adapter.mapper;

import com.cade.api.dto.UserDTO;
import com.cade.core.domain.entity.UserEntity;
import com.cade.core.utils.Constants;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    componentModel = Constants.MAPPER_COMPONENT_MODEL,
    uses = ObjectIdMapper.class
)
public interface UserMapper {
    UserDTO toDTO(UserEntity source);
}
