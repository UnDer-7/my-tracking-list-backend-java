package com.cade.features.user.auth;

import com.cade.features.user.ResponseUserDTO;
import com.cade.features.user.User;
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
    User toAuth(RequestRegisterDTO dto);

    @Mapping(target = "id", expression = "java(entity.getId().toHexString())")
    ResponseUserDTO toResponseUserDTO(User entity);
}
