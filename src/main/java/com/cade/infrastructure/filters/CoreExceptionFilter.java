package com.cade.infrastructure.filters;

import com.cade.exceptions.CoreException;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class CoreExceptionFilter implements ExceptionMapper<CoreException> {

    @Override
    public Response toResponse(final CoreException exception) {
        exception.logErr();
        return Response
            .status(exception.getStatusCode())
            .entity(exception)
            .build();
    }
}
