package com.cade.core.ports.driven;

import com.cade.core.domain.UserDomain;

public interface UserRepository {
    UserDomain getUser();
}
