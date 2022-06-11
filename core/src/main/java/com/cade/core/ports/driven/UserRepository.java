package com.cade.core.ports.driven;

import com.cade.core.domain.entity.UserEntity;
import io.smallrye.mutiny.Uni;

public interface UserRepository {

    Uni<UserEntity> save(UserEntity domain);

    Uni<Boolean> userExists(String email);

    Uni<UserEntity> findByEmail(String email);
}
