package com.cade.features.user.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
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
}
