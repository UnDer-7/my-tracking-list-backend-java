package com.cade.backend.exceptions;

import com.cade.core.exceptions.CoreExceptions;
import com.cade.core.exceptions.ErrorMessages;

import javax.ws.rs.core.Response;

public class UnauthorizedException extends CoreExceptions {
    public UnauthorizedException() {
        super(ErrorMessages.INVALID_TOKEN, Response.Status.UNAUTHORIZED);
    }
}
