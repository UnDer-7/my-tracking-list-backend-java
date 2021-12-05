package com.cade.user;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "cdi",
    builder = @Builder(disableBuilder = true),
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {
    @Mapping(target = "id", expression = "java(user.getId().toHexString())")
    ResponseUserDTO toResponseUserDTO(User user);
}
