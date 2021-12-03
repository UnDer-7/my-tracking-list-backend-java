package com.cade.api.v1.auth;

import com.cade.api.v1.user.ResponseUserDTO;
import io.smallrye.mutiny.Uni;

public interface AuthRestService {
    Uni<ResponseUserDTO> register(RegisterDTO registerDTO);
    Uni<ResponseUserDTO> signIn(RegisterDTO registerDTO);
}
