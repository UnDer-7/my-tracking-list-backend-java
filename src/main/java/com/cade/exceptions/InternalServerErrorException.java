package com.cade.exceptions;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;

@Slf4j
public class InternalServerErrorException extends CoreException {
    public InternalServerErrorException(final Throwable cause) {
        super(ErrorMessages.INTERNAL_SERVER_ERROR, Response.Status.INTERNAL_SERVER_ERROR, cause);
    }

    public InternalServerErrorException() {
        super(ErrorMessages.INTERNAL_SERVER_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void logErr() {
      log.error("An InternalServerError just occurred\tCause ", getCause());
    }
}
