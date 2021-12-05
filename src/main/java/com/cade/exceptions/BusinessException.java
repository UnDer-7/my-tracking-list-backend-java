package com.cade.exceptions;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class BusinessException extends CoreException {
    public BusinessException(final ErrorMessages message) {
        super(message, 422);
        log.warn("BusinessException was thrown | Msg: {}", message.name()); // todo: logar no exceptionHandler
    }

    public static Supplier<BusinessException> throwException(ErrorMessages message) {
        return () -> new BusinessException(message);
    }
}
