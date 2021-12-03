package com.cade.backend.adapter.mapper;

import com.cade.api.v1.auth.RegisterDTO;
import com.cade.api.v1.user.ResponseUserDTO;
import com.cade.core.domain.User;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "cdi",
    builder = @Builder(disableBuilder = true),
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AuthMapper {
    User toAuth(RegisterDTO dto);

    @Mapping(target = "id", expression = "java(entity.getId().toHexString())")
    ResponseUserDTO toResponseUserDTO(User entity);
}
