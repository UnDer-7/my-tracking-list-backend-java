package com.cade.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    INVALID_TOKEN(
        "000001",
        "Invalid token",
        "Token is: expired, has been tampered or is invalid format"
    ),
    INTERNAL_SERVER_ERROR(
        "000002",
        "Something went wrong",
        "Just another internal server error"
    );

    private final String errCode;
    private final String userMsg;
    private final String devMsg;
    private String howToSolve;

    ErrorMessages(
        final String errCode,
        final String userMsg,
        final String devMsg) {

        this.userMsg = userMsg;
        this.devMsg = devMsg;
        this.errCode = errCode;
    }
}
