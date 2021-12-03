package com.cade.core.ports.driven;

import com.cade.core.domain.Token;
import io.smallrye.mutiny.Uni;

public interface TokenValidatorHandler {
    Uni<Token> decode(String token);
}
