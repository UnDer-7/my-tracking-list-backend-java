package com.cade.api.controller;

import com.cade.api.dto.TokenDTO;
import io.smallrye.mutiny.Uni;

public interface AuthController {
    Uni<TokenDTO> signIn(String authCode);
}
