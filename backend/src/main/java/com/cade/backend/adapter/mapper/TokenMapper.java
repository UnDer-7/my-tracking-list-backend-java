package com.cade.backend.adapter.mapper;

import com.cade.api.dto.TokenDTO;
import com.cade.core.domain.TokenDomain;
import com.cade.core.utils.Constants;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.ZoneId;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface TokenMapper {

    TokenDTO toDTO(TokenDomain source);

    default TokenDomain toDomain(final GoogleIdToken googleToken, final String refreshTokenEncoded, final String tokenEncoded) {
        final var tkn = googleToken.getPayload();

        return TokenDomain
            .builder()
            .email(tkn.getEmail())
            .isEmailVerified(tkn.getEmailVerified())
            .expirationTime(
                Instant.ofEpochMilli((Long) tkn.get("exp") * 1000)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime())
            .issuedAt(
                Instant.ofEpochMilli((Long) tkn.get("iat") * 1000)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime())
            .issuer((String) tkn.get("iss"))
            .jwtId((String) tkn.get("jti"))
            .subject((String) tkn.get("sub"))
            .name((String) tkn.get("name"))
            .pictureUrl((String) tkn.get("picture"))
            .givenName((String) tkn.get("given_name"))
            .familyName((String) tkn.get("family_name"))
            .locale((String) tkn.get("locale"))
            .refreshTokenEncoded(refreshTokenEncoded)
            .tokenEncoded(tokenEncoded)
            .build();
    }

}
