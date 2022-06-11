package com.cade.core.service;

import com.cade.core.domain.ErrorMessages;
import com.cade.core.domain.TokenDomain;
import com.cade.core.domain.entity.UserEntity;
import com.cade.core.exception.NotFoundException;
import com.cade.core.exception.UnprocessableEntityException;
import com.cade.core.ports.driven.TokenHandler;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.ports.driver.AuthService;
import com.cade.core.utils.Assert;
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

    @Override
    public Uni<TokenDomain> signIn(final String authCode) {
        return tokenHandler.getToken(authCode)
            .flatMap(tkn -> userRepository.userExists(tkn.getEmail())
                .map(doesUserExists -> {
                    Assert.thatIsTrue(doesUserExists, new NotFoundException(ErrorMessages.USER_NOT_REGISTERED));
                    return tkn;
                }));
    }

    @Override
    public Uni<TokenDomain> register(final String authCode) {
        return tokenHandler.getToken(authCode)
            .flatMap(tkn -> userRepository.userExists(tkn.getEmail())
                .map(doesUserExists -> {
                    Assert.thatIsFalse(doesUserExists, new UnprocessableEntityException(ErrorMessages.USER_ALREADY_REGISTERED));
                    return tkn;
                }))
            .flatMap(tkn -> userRepository.save(UserEntity.fromToken(tkn)).replaceWith(tkn));
    }

}
