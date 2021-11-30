package com.cade.backend.exceptions;

import com.cade.core.exceptions.CoreExceptions;
import com.cade.core.exceptions.ErrorMessages;

import javax.ws.rs.core.Response;

public class InternalServerErrorException extends CoreExceptions {
    public InternalServerErrorException() {
        super(ErrorMessages.INTERNAL_SERVER_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
    }
}
