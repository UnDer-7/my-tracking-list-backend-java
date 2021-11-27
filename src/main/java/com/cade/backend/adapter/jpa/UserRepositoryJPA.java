package com.cade.backend.adapter.jpa;

import com.cade.core.domain.User;
import com.cade.core.ports.driven.UserRepository;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepositoryJPA implements UserRepository, ReactivePanacheMongoRepository<User> {

    @Override
    public Uni<User> save(final User user) {
        return this.persist(user);
    }
}
