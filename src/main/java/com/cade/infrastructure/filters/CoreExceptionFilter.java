package com.cade.infrastructure.filters;

import com.cade.exceptions.CoreException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CoreExceptionFilter implements ExceptionMapper<CoreException> {

    @Override
    public Response toResponse(final CoreException exception) {
        return Response
            .status(exception.getStatusCode())
            .entity(exception)
            .build();
    }
}
