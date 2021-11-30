package com.cade.backend.filters;

import com.cade.backend.exceptions.InternalServerErrorException;
import com.cade.backend.exceptions.UnauthorizedException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @see <a href="https://quarkus.io/guides/security-customization">https://quarkus.io/guides/security-customization</a>
 */
@Slf4j
@Provider
@PreMatching
public class TokenValidationFilter implements ContainerRequestFilter {

    @ConfigProperty(name = "MY_TRACKING_LIST_BACKEND_GOOGLE_CLIENT_ID")
    private String clientId;

    @Override
    public void filter(final ContainerRequestContext reqCtx) throws IOException {
        Optional.ofNullable(reqCtx.getHeaderString(HttpHeaders.AUTHORIZATION))
                .map(bearerToken -> bearerToken.split(" ")[1])
                .filter(Predicate.not(String::isBlank))
                .map(this::validateToken)
                .ifPresent(token -> reqCtx.setSecurityContext(getSecurityContext(token, reqCtx.getUriInfo().getAbsolutePath().toString())));

    }

    private SecurityContext getSecurityContext(final GoogleIdToken token, final String url) {
        return new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return () -> token.getPayload().getEmail();
            }

            @Override
            public boolean isUserInRole(final String role) {
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

    /**
     * @see <a href="https://developers.google.com/identity/sign-in/android/backend-auth">
     *     https://developers.google.com/identity/sign-in/android/backend-auth
     *     </a>
     */
    private GoogleIdToken validateToken(final String tokenStr) {
        try {
            final var token = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(clientId))
                .build()
                .verify(tokenStr);
            if (Objects.isNull(token)) {
                throw new UnauthorizedException();
            }
            return token;
        } catch (IOException | GeneralSecurityException e) {
            throw new InternalServerErrorException();
        }
    }
}
