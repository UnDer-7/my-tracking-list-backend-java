package com.cade.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class TokenDomain {
    private String email;
    private boolean isEmailVerified;
    private LocalDateTime expirationTime;
    private LocalDateTime issuedAt;
    private String issuer;
    private String jwtId;
    private String subject;
    private String name;
    private String pictureUrl;
    private String givenName;
    private String familyName;
    private String locale;
    private String refreshTokenEncoded;
    private String tokenEncoded;
}
