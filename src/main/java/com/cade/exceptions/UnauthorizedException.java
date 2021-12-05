package com.cade.exceptions;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;

@Slf4j
public class UnauthorizedException extends CoreException {
    public UnauthorizedException() {
        super(ErrorMessages.INVALID_TOKEN, Response.Status.UNAUTHORIZED);
    }
}
