package com.cade.exceptions;

import javax.ws.rs.core.Response;

public class InternalServerErrorException extends CoreException {
    public InternalServerErrorException() {
        super(ErrorMessages.INTERNAL_SERVER_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
    }
}
