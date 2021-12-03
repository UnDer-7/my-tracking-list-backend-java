package com.cade.core.ports.driven;

import com.cade.core.domain.User;
import io.smallrye.mutiny.Uni;

public interface UserRepository {
    Uni<User> save(User user);
    Uni<User> findByEmail(String email);
    Uni<Boolean> userExists(final String email);
}
