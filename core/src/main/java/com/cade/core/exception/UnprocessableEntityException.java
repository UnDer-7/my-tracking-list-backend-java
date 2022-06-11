package com.cade.core.exception;

import com.cade.core.domain.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class UnprocessableEntityException extends CoreException {

    public UnprocessableEntityException(final ErrorMessages errorMessages) {
        super(errorMessages, StatusCode.UNPROCESSABLE_ENTITY);
    }

    @Override
    protected LogLevel getLogLevel() {
        return LogLevel.WARN;
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

}
