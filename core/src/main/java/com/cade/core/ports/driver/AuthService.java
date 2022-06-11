package com.cade.core.ports.driver;

import com.cade.core.domain.TokenDomain;
import io.smallrye.mutiny.Uni;

public interface AuthService {

    Uni<TokenDomain> signIn(String authCode);

    Uni<TokenDomain> register(String authCode);

    Uni<TokenDomain> refreshToken(String refreshToken);

}
