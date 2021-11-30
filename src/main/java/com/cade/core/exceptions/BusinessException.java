package com.cade.core.exceptions;

public class BusinessException extends CoreExceptions {
    public BusinessException(final ErrorMessages message) {
        super(message, 422);
    }
}
