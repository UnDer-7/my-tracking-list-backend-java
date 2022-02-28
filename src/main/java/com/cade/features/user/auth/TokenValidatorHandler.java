//package com.cade.features.user.auth;
//
//import com.cade.exceptions.InternalServerErrorException;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.gson.GsonFactory;
//import io.smallrye.mutiny.Uni;
//import lombok.extern.slf4j.Slf4j;
//import org.eclipse.microprofile.config.inject.ConfigProperty;
//
//import javax.enterprise.context.ApplicationScoped;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.time.Instant;
//import java.time.ZoneId;
//import java.util.Collections;
//import java.util.Objects;
//import java.util.regex.Pattern;
//
///**
// * @see <a href="https://developers.google.com/identity/sign-in/android/backend-auth">
// *     https://developers.google.com/identity/sign-in/android/backend-auth
// *     </a>
// */
//@Slf4j
//@ApplicationScoped
//public class TokenValidatorHandler {
//
//    @ConfigProperty(name = "MY_TRACKING_LIST_BACKEND_GOOGLE_CLIENT_ID")
//    private String clientId;
//
//    public Uni<Token> decode(final String token) {
//        return Uni.createFrom().item(() -> verifyToken(token));
//    }
//
//    public Token verifyToken(final String token) {
//        try {
//            final var isTokenValid = Pattern
//                .compile("(^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$)")
//                .matcher(token)
//                .find();
//            if (!isTokenValid) return null;
//
//            final var tokenDecoded = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
//                .setAudience(Collections.singletonList(clientId))
//                .build()
//                .verify(token);
//            if (Objects.isNull(tokenDecoded)) return null;
//
//            return mapToken(tokenDecoded);
//        } catch (IOException | GeneralSecurityException e) {
//            e.printStackTrace();
//            throw new InternalServerErrorException();
//        }
//    }
//
//    private Token mapToken(GoogleIdToken googleIdToken) {
//        final var tkn = googleIdToken.getPayload();
//
//        return Token
//            .builder()
//            .email(tkn.getEmail())
//            .isEmailVerified(tkn.getEmailVerified())
//            .expirationTime(
//                Instant.ofEpochMilli((Long) tkn.get("exp") * 1000)
//                    .atZone(ZoneId.systemDefault())
//                    .toLocalDateTime())
//            .issuedAt(
//                Instant.ofEpochMilli((Long) tkn.get("iat") * 1000)
//                    .atZone(ZoneId.systemDefault())
//                    .toLocalDateTime())
//            .issuer((String) tkn.get("iss"))
//            .jwtId((String) tkn.get("jti"))
//            .subject((String) tkn.get("sub"))
//            .name((String) tkn.get("name"))
//            .pictureUrl((String) tkn.get("picture"))
//            .givenName((String) tkn.get("given_name"))
//            .familyName((String) tkn.get("family_name"))
//            .locale((String) tkn.get("locale"))
//            .build();
//    }
//}
