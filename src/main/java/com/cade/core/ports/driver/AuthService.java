package com.cade.core.ports.driver;

import com.cade.core.domain.User;
import io.smallrye.mutiny.Uni;

public interface AuthService {
    Uni<User> register(String token);
    Uni<User> signIn(String token);
}
