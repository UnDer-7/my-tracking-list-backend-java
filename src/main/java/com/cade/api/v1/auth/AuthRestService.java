package com.cade.api.v1.auth;

import com.cade.api.v1.user.ResponseUserDTO;
import io.smallrye.mutiny.Uni;

public interface AuthRestService {
    Uni<ResponseUserDTO> signUp(SignUpDTO signUp);
}
