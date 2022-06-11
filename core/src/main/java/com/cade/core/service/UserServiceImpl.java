package com.cade.core.service;

import com.cade.core.domain.entity.UserEntity;
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
    public Uni<UserEntity> save(UserEntity domain) {
        return userRepository.save(domain);
    }

    @Override
    public Uni<Boolean> userExists(final String email) {
        return userRepository.userExists(email);
    }

}
