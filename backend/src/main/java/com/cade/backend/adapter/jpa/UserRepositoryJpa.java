package com.cade.backend.adapter.jpa;

import com.cade.core.domain.entity.UserEntity;
import com.cade.core.ports.driven.UserRepository;
import com.mongodb.client.model.CountOptions;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class UserRepositoryJpa implements UserRepository, ReactivePanacheMongoRepository<UserEntity> {

    @Override
    public Uni<UserEntity> save(final UserEntity domain) {
        return this.persist(domain);
    }

    @Override
    public Uni<Boolean> userExists(final String email) {
        return this.mongoCollection()
            .countDocuments(new Document("email", email), new CountOptions().limit(1))
            .map(quantity -> quantity == 1);
    }

}
