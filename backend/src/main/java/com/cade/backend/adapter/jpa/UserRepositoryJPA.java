package com.cade.backend.adapter.jpa;

import com.cade.core.domain.User;
import com.cade.core.ports.driven.UserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJPA extends UserRepository, MongoRepository<User, String> {

    @Override
    default User create(User user) {
        return this.save(user);
    }

}
