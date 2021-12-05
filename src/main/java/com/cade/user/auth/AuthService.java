package com.cade.user.auth;

import com.cade.user.User;
import com.cade.exceptions.BusinessException;
import com.cade.exceptions.ErrorMessages;
import com.cade.user.UserRepositoryJPA;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class AuthService {

    private final UserRepositoryJPA repository;
    private final TokenValidatorHandler tokenHandler;

    public Uni<User> register(final String token) {
        return tokenHandler.decode(token)
            .onItem()
            .ifNull()
            .failWith(new BusinessException(ErrorMessages.INVALID_TOKEN))
            .flatMap(tokenDecoded -> repository.userExists(tokenDecoded.getEmail())
                .map(buildUserFromToken(tokenDecoded)))
            .flatMap(repository::save);
    }

    public Uni<User> signIn(final String token) {
        return tokenHandler.decode(token)
            .onItem()
            .ifNull()
            .failWith(new BusinessException(ErrorMessages.INVALID_TOKEN))
            .map(Token::getEmail)
            .flatMap(repository::findByEmail)
            .onItem()
            .ifNull()
            .failWith(new BusinessException(ErrorMessages.USER_NOT_REGISTERED));
    }

    private Function<Boolean, User> buildUserFromToken(final Token token) {
        return userExists -> {
            if (Boolean.TRUE.equals(userExists)) throw new BusinessException(ErrorMessages.USER_ALREADY_REGISTERED);

            final var user = new User(
                token.getEmail(),
                token.getName(),
                token.getGivenName(),
                token.getFamilyName(),
                token.getPictureUrl()
            );

            return validateUser(user);
        };
    }

    private User validateUser(final User user) {
        if (Objects.isNull(user.getEmail())) {
            throw new BusinessException(ErrorMessages.INVALID_USER_EMAIL);
        }

        if (Objects.isNull(user.getName())) {
            throw new BusinessException(ErrorMessages.INVALID_USER_NAME);
        }

        return user;
    }
}
