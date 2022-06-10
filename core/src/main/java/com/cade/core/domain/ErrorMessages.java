package com.cade.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
@RequiredArgsConstructor
public enum ErrorMessages {
    USER_NOT_REGISTERED(
        "001",
        "You are not registered yet"
    );

    private final String errCode;
    private final String userMsg;
    @Setter private String customMsg;
}
