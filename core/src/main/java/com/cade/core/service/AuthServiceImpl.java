package com.cade.core.service;

import com.cade.core.domain.ErrorMessages;
import com.cade.core.domain.TokenDomain;
import com.cade.core.exception.NotFoundException;
import com.cade.core.ports.driven.TokenHandler;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.ports.driver.AuthService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenHandler tokenHandler;
    private final UserRepository userRepository;

    public Uni<TokenDomain> signIn(final String authCode) {
        return tokenHandler.getToken(authCode)
            .flatMap(tkn -> userRepository.userExists(tkn.getEmail())
                    .map(doesUserExists -> handleUser(doesUserExists, tkn)));
    }

    private TokenDomain handleUser(final Boolean doesUserExists, final TokenDomain tkn) {
        if (Boolean.TRUE.equals(doesUserExists)) return tkn;
        throw new NotFoundException(ErrorMessages.USER_NOT_REGISTERED);
    }
}
