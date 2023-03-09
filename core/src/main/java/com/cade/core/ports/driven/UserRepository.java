package com.cade.core.ports.driven;

import com.cade.core.domain.User;

public interface UserRepository {

    User create(User user);
}
