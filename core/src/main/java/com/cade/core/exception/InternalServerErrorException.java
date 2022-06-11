package com.cade.core.exception;

import com.cade.core.domain.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Slf4j
public class InternalServerErrorException extends CoreException {

    public InternalServerErrorException(final ErrorMessages errorMessages) {
        super(errorMessages, StatusCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(final ErrorMessages errorMessages, final Throwable throwable) {
        super(errorMessages, StatusCode.INTERNAL_SERVER_ERROR, throwable);
    }

    public static Supplier<CoreException> exceptionSupplier(final ErrorMessages errorMessages) {
        return () -> new InternalServerErrorException(errorMessages);
    }

    @Override
    protected LogLevel getLogLevel() {
        return LogLevel.ERROR;
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

}
