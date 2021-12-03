package com.cade.backend.filters;

import com.cade.backend.exceptions.UnauthorizedException;
import com.cade.core.domain.Token;
import com.cade.core.ports.driven.TokenValidatorHandler;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @see <a href="https://quarkus.io/guides/resteasy-reactive#request-or-response-filters">
 *     https://quarkus.io/guides/resteasy-reactive#request-or-response-filters
 * </a>
 */
@Slf4j
@ApplicationScoped
@RequiredArgsConstructor(onConstructor_={@Inject})
public class TokenFilter {
    private final TokenValidatorHandler tokenValidatorHandler;

    @ServerRequestFilter(preMatching = true)
    public Uni<Void> filter(ContainerRequestContext reqCtx) {
         return Uni.createFrom()
            .item(() -> getToken(reqCtx))
            .onItem()
            .ifNotNull()
            .transformToUni(token -> tokenValidatorHandler.decode(token)
                    .onItem()
                    .ifNull()
                    .failWith(UnauthorizedException::new)
                    .map(Token::getEmail)
                    .flatMap(email -> {
                        reqCtx.setSecurityContext(getSecurityContext(email, reqCtx));
                        return Uni.createFrom().nullItem();
                    }));
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
