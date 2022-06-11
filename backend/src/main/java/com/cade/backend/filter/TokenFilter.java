package com.cade.backend.filter;

import com.cade.backend.adapter.mapper.TokenMapper;
import com.cade.backend.validator.TokenValidator;
import com.cade.core.domain.ErrorMessages;
import com.cade.core.exception.NotFoundException;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.utils.Assert;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @see <a href="https://quarkus.io/guides/resteasy-reactive#request-or-response-filters">
 * https://quarkus.io/guides/resteasy-reactive#request-or-response-filters
 * </a>
 */
@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class TokenFilter {

    private final TokenValidator tokenValidator;
    private final TokenMapper tokenMapper;
    private final UserRepository userRepository;

    @ServerRequestFilter(preMatching = true)
    public Uni<Void> filter(final ContainerRequestContext reqCtx) {
       return Uni.createFrom()
            .item(() -> getToken(reqCtx))
            .onItem().ifNotNull()
            .transformToUni(token -> tokenValidator.decodeToken(token)
                    .onItem().ifNull().failWith(new RuntimeException("Token Invalid")) // todo: arrumar exception
                    .map(googleToken -> tokenMapper.toDomain(googleToken, null, token))
                    .flatMap(tokenDomain -> userRepository.userExists(tokenDomain.getEmail())
                            .onItem().ifNull()
                            .failWith(new NotFoundException(ErrorMessages.USER_NOT_REGISTERED))
                            .flatMap(userExists -> {
                                Assert.thatIsTrue(userExists, new NotFoundException(ErrorMessages.USER_NOT_REGISTERED));

                                reqCtx.setSecurityContext(getSecurityContext(tokenDomain.getEmail(), reqCtx));
                                return Uni.createFrom().nullItem();
                            })));
    }


    private SecurityContext getSecurityContext(final String email, final ContainerRequestContext reqCtx) {
        final var url = reqCtx.getUriInfo().getAbsolutePath().toString();

        return new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return () -> email;
            }

            @Override
            public boolean isUserInRole(final String role) {
                log.info("###### Verifying Roles: {}", role);
                return true;
            }

            @Override
            public boolean isSecure() {
                return url.startsWith("https");
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        };
    }

    private String getToken(ContainerRequestContext reqCtx) {
        return Optional.ofNullable(reqCtx.getHeaderString(HttpHeaders.AUTHORIZATION))
            .map(bearerToken -> bearerToken.split(" "))
            .map(Arrays::asList)
            .filter(arr -> arr.size() == 2)
            .map(arr -> arr.get(1))
            .filter(Predicate.not(String::isBlank))
            .orElse(null);
    }

}
