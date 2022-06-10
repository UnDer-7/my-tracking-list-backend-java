package com.cade.backend.validator;

import com.cade.backend.config.ServerConfig;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.function.Supplier;


@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class TokenValidator {
    private final GoogleIdTokenVerifier googleIdTokenVerifier;

    public Uni<GoogleIdToken> decodeToken(final String token) {
        final Supplier<GoogleIdToken> execute = () -> {
            try {
                final var idToken = GoogleIdToken.parse(googleIdTokenVerifier.getJsonFactory(), token);

                // todo: fazer cache do jwt
                if (!googleIdTokenVerifier.verify(idToken)) {
                    return null;
                }

                return idToken;
            } catch (IOException | GeneralSecurityException e) {
                log.warn("Error while verifying Token");
                e.printStackTrace();
                throw new RuntimeException(e); // todo: trocar exception
            }
        };

        return Uni.createFrom()
            .item(execute)
            .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }
}
