//package com.cade.backend.auth;
//
//import com.cade.core.ports.driven.UserRepository;
//import io.smallrye.mutiny.Uni;
//import io.smallrye.mutiny.unchecked.Unchecked;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.jboss.resteasy.reactive.server.ServerRequestFilter;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.SecurityContext;
//import java.security.Principal;
//import java.util.Arrays;
//import java.util.Optional;
//import java.util.function.Predicate;
//
///**
// * @see <a href="https://quarkus.io/guides/resteasy-reactive#request-or-response-filters">
// * https://quarkus.io/guides/resteasy-reactive#request-or-response-filters
// * </a>
// */
//@Slf4j
//@ApplicationScoped
//@RequiredArgsConstructor
//public class TokenFilter {
//
//    private final TokenValidatorHandler tokenValidatorHandler;
//    private final UserRepository userRepository;
//
//    @ServerRequestFilter(preMatching = true)
//    public Uni<Void> filter(final ContainerRequestContext reqCtx) {
//        log.info("Entrou no filtro");
//       return Uni.createFrom()
//            .item(() -> getToken(reqCtx))
//            .onItem().ifNotNull()
//            .transformToUni(token -> tokenValidatorHandler.decodeToken(token)
//                    .onItem().ifNull()
//                    .failWith(new RuntimeException("Token Invalid")) // todo: arrumar exception
//                    .map(TokenDTO::getEmail)
//                    .flatMap(email -> userRepository.userExists(email)
//                            .onItem().ifNull()
//                            .failWith(new RuntimeException("User NOT REGISTRED!")) // todo: arrumar exception
//                            .flatMap(Unchecked.function(userExists -> {
//                                if (Boolean.FALSE.equals(userExists)) throw new RuntimeException("USER NOT REGISTRED!"); // todo: arrumar expcetion
//
//                                reqCtx.setSecurityContext(getSecurityContext(email, reqCtx));
//                                return Uni.createFrom().nullItem();
//                            }))));
//    }
//
//
//    private SecurityContext getSecurityContext(final String email, final ContainerRequestContext reqCtx) {
//        final var url = reqCtx.getUriInfo().getAbsolutePath().toString();
//
//        return new SecurityContext() {
//            @Override
//            public Principal getUserPrincipal() {
//                return () -> email;
//            }
//
//            @Override
//            public boolean isUserInRole(final String role) {
//                log.info("###### Verifying Roles: {}", role);
//                return true;
//            }
//
//            @Override
//            public boolean isSecure() {
//                return url.startsWith("https");
//            }
//
//            @Override
//            public String getAuthenticationScheme() {
//                return null;
//            }
//        };
//    }
//
//    private String getToken(ContainerRequestContext reqCtx) {
//        return Optional.ofNullable(reqCtx.getHeaderString(HttpHeaders.AUTHORIZATION))
//            .map(bearerToken -> bearerToken.split(" "))
//            .map(Arrays::asList)
//            .filter(arr -> arr.size() == 2)
//            .map(arr -> arr.get(1))
//            .filter(Predicate.not(String::isBlank))
//            .orElse(null);
//    }
//
//}
