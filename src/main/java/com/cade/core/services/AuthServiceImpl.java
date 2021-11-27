package com.cade.core.services;

import com.cade.core.domain.User;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.ports.driver.AuthService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;

    @Override
    public Uni<User> singUp(final User user) {
        return repository.save(user);
    }
}
