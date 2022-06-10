package com.cade.core.exception;

import com.cade.core.domain.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class NotFoundException extends CoreException {

    public NotFoundException(final ErrorMessages errorMessages) {
        super(errorMessages, StatusCode.NOT_FOUND);
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
