package com.cade.backend.adapter.rest;

import com.cade.api.dto.UserDTO;
import com.cade.api.v1.UserRestService;
import com.cade.backend.adapter.mapper.UserMapper;
import com.cade.core.ports.driver.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestServiceImpl implements UserRestService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDTO create(final UserDTO dto) {
        log.info("USER: {}", dto);

        final var user = userService.create(userMapper.toEntity(dto));

        return userMapper.toDTO(user);
    }

}
