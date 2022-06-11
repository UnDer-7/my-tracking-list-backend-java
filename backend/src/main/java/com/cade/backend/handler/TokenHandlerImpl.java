package com.cade.backend.handler;

import com.cade.backend.adapter.clients.google.OAuthGoogleClient;
import com.cade.backend.adapter.mapper.TokenMapper;
import com.cade.backend.validator.TokenValidator;
import com.cade.core.domain.TokenDomain;
import com.cade.core.ports.driven.TokenHandler;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class TokenHandlerImpl implements TokenHandler {

    private final OAuthGoogleClient oAuthGoogleClient;
    private final TokenValidator tokenValidator;
    private final TokenMapper tokenMapper;

    @Override
    public Uni<TokenDomain> getToken(final String authCode) {
        return oAuthGoogleClient.getAccessToken(authCode)
            .flatMap(oauthResponse -> tokenValidator.decodeToken(oauthResponse.idToken())
                .onItem().ifNull().failWith(new RuntimeException("Token Invalid")) // todo: arrumar exception
                .map(idToken -> tokenMapper.toDomain(idToken, oauthResponse.refreshToken(), oauthResponse.idToken())));
    }

    @Override
    public Uni<TokenDomain> refreshToken(final String refreshToken) {
        return oAuthGoogleClient.refreshToken(refreshToken)
            .flatMap(oauthResponse -> tokenValidator.decodeToken(oauthResponse.idToken())
                    .onItem().ifNull().failWith(new RuntimeException("Token Invalid")) // todo: arrumar exception
                    .map(idToken -> tokenMapper.toDomain(idToken, refreshToken, oauthResponse.idToken())));
    }
}
