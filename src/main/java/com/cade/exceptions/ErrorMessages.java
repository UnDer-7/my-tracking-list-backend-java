package com.cade.exceptions;

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
    ),
    INVALID_USER_EMAIL(
        "000003",
        "It wasn't possible to obtain your email from your account provider", // todo: verify english
        "The informed token doesn't contain an email"
    ),
    INVALID_USER_NAME(
        "000004",
        "It wasn't possible to obtain your name from your account provider", // todo: verify english
        "The informed token doesn't contain an name"
    ),
    USER_ALREADY_REGISTERED(
        "000005",
        "Your email is already registered",
        "The informed email is already in use"
    ),
    USER_NOT_REGISTERED(
        "000005",
        "You are not registered yet",
        "Before trying to sign in, you have to to register"
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
