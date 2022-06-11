package com.cade.core.service;

import com.cade.core.domain.ErrorMessages;
import com.cade.core.domain.entity.UserEntity;
import com.cade.core.exception.NotFoundException;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.ports.driver.UserService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Uni<UserEntity> getUserByEmail(final String email) {
        return userRepository.findByEmail(email)
            .onItem().ifNull().failWith(new NotFoundException(ErrorMessages.USER_NOT_FOUND.setCustomMsg("Email used: %s".formatted(email))));
    }


}
