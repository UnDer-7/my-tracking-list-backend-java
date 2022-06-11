package com.cade.core.ports.driver;

import com.cade.core.domain.entity.UserEntity;
import io.smallrye.mutiny.Uni;

public interface UserService {

    Uni<UserEntity> getUserByEmail(String email);
}
