package com.cade.core.services;

import com.cade.core.domain.User;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.ports.driver.UserService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.function.Predicate;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_={@Inject})
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public Uni<User> findByEmail(final String email) {
        return Optional.ofNullable(email)
            .filter(Predicate.not(String::isBlank))
            .map(repository::findByEmail)
            .orElseGet(Uni.createFrom()::nullItem);
    }
}
