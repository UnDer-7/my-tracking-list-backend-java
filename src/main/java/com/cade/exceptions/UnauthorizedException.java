package com.cade.exceptions;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;

@Slf4j
public class UnauthorizedException extends CoreException {
    public UnauthorizedException(final ErrorMessages message) {
        super(message, Response.Status.UNAUTHORIZED);
    }

    @Override
    public void logErr() {
        log.warn("An UnauthorizedException just occurred\tErrorMessage: {}", getErrorMessage().name());
    }
}
