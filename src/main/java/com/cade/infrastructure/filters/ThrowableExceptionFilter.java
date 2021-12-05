//package com.cade.infrastructure.filters;
//
//import com.cade.exceptions.CoreException;
//
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.ExceptionMapper;
//import javax.ws.rs.ext.Provider;
//
//@Provider
//public class ThrowableExceptionFilter implements ExceptionMapper<Throwable> {
//
//    @Override
//    public Response toResponse(final Throwable exception) {
//        if (exception instanceof CoreException) {
//            // todo: fazer algo
//            throw new RuntimeException("TODO: Criar filtro pra todas expetions");
//        }
//
//        throw new RuntimeException("TODO: Criar filtro pra todas expetions");
//    }
//}
