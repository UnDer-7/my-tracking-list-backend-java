package com.cade.features.user;

import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.function.Predicate;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_={@Inject})
public class UserService {
    private final UserRepositoryJPA repository;

    public Uni<User> findByEmail(final String email) {
        return Optional.ofNullable(email)
            .filter(Predicate.not(String::isBlank))
            .map(repository::findByEmail)
            .orElseGet(Uni.createFrom()::nullItem);
    }
}
