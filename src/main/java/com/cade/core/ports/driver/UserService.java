package com.cade.core.ports.driver;

import com.cade.core.domain.User;
import io.smallrye.mutiny.Uni;

public interface UserService {
    Uni<User> findByEmail(String email);
}
