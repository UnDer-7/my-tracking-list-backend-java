package com.cade.user.auth;

import com.cade.user.auth.RequestRegisterDTO;
import com.cade.user.ResponseUserDTO;
import com.cade.user.User;
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
