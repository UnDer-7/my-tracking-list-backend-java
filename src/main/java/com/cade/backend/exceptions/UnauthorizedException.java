package com.cade.backend.exceptions;

import com.cade.core.exceptions.CoreExceptions;
import com.cade.core.exceptions.ErrorMessages;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;

@Slf4j
public class UnauthorizedException extends CoreExceptions {
    public UnauthorizedException() {
        super(ErrorMessages.INVALID_TOKEN, Response.Status.UNAUTHORIZED);
    }
}
