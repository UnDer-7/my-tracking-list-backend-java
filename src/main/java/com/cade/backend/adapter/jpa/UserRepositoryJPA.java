package com.cade.backend.adapter.jpa;

import com.cade.core.domain.User;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.utils.OptionalHelper;
import com.mongodb.client.model.CountOptions;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class UserRepositoryJPA implements UserRepository, ReactivePanacheMongoRepository<User> {

    @Override
    public Uni<User> save(final User user) {
        return this.persist(user);
    }

    @Override
    public Uni<User> findByEmail(final String email) {
        return this.find("email", email)
            .singleResultOptional()
            .map(OptionalHelper::orElseNull);
    }

    @Override
    public Uni<Boolean> userExists(final String email) {
        return this.mongoCollection()
            .countDocuments(new Document("email", email), new CountOptions().limit(1))
            .map(quantity -> quantity == 1);
    }
}
