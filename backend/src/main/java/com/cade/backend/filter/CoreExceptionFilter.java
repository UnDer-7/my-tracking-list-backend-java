package com.cade.backend.filter;

import com.cade.backend.adapter.mapper.CoreExceptionMapper;
import com.cade.core.exception.CoreException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
@RequiredArgsConstructor
public class CoreExceptionFilter implements ExceptionMapper<CoreException> {

    private final CoreExceptionMapper coreExceptionMapper;

    @Override
    public Response toResponse(final CoreException exception) {
        exception.executeLogging();

        final var dto = coreExceptionMapper.toDTO(exception);

        return Response
            .status(dto.statusCode())
            .entity(dto)
            .build();
    }

}
