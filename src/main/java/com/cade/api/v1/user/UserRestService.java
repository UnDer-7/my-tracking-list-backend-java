package com.cade.api.v1.user;

import io.smallrye.mutiny.Uni;

public interface UserRestService {
    Uni<ResponseUserDTO> findByEmail(String email);
}
