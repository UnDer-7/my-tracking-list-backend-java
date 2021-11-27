package com.cade.core.services;

import com.cade.core.domain.User;
import com.cade.core.ports.driver.UserService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public Uni<User> findByEmail(final String email) {
        return Uni.createFrom().nullItem();
    }
}
