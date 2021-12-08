package com.cade.features.user;

import com.cade.shared.OptionalHelper;
import com.mongodb.client.model.CountOptions;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class UserRepositoryJPA implements ReactivePanacheMongoRepository<User> {

    public Uni<User> save(final User user) {
        return this.persist(user);
    }

    public Uni<User> findByEmail(final String email) {
        return this.find("email", email)
            .singleResultOptional()
            .map(OptionalHelper::orElseNull);
    }

    public Uni<Boolean> userExists(final String email) {
        return this.mongoCollection()
            .countDocuments(new Document("email", email), new CountOptions().limit(1))
            .map(quantity -> quantity == 1);
    }
}
