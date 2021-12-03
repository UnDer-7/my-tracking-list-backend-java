package com.cade.backend.exceptions;

import com.cade.core.exceptions.CoreExceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<CoreExceptions> {

    @Override
    public Response toResponse(final CoreExceptions exception) {
        return Response
            .status(exception.getStatusCode())
            .entity(exception)
            .build();
    }
}
