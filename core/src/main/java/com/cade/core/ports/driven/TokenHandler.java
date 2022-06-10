package com.cade.core.ports.driven;

import com.cade.core.domain.TokenDomain;
import io.smallrye.mutiny.Uni;

public interface TokenHandler {
    Uni<TokenDomain> getToken(String authCode);
}
