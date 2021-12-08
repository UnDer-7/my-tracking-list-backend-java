package com.cade.exceptions;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class BusinessException extends CoreException {
    public BusinessException(final ErrorMessages message) {
        super(message, 422);
    }

    public static Supplier<BusinessException> throwException(ErrorMessages message) {
        return () -> new BusinessException(message);
    }

    @Override
    public void logErr() {
        log.warn("An BusinessException just occurred\tErrorMessage: {}", getErrorMessage().name());
    }
}
