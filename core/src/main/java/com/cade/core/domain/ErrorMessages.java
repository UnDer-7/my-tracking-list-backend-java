package com.cade.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@Accessors(chain = true)
@RequiredArgsConstructor
public enum ErrorMessages {
    UNEXPECTED_ERROR(
        "001",
        "Some unexpected error occurred"
    ),
    USER_NOT_REGISTERED(
        "002",
        "You are not registered yet"
    ),
    USER_ALREADY_REGISTERED(
        "003",
        "User is already registered"
    ),
    ERROR_MESSAGE_NOT_FOUND(
        "004",
        "Enum ErrorMessages was not found"
    ),
    SAVING_ENTITY_CANNOT_HAVE_AN_ID(
        "005",
        "Entity cannot have an ID when saving"
    );

    private final String errCode;
    private final String userMsg;
    @Setter private String customMsg;

    public static Optional<ErrorMessages> getByErrCode(final String errCode) {
        return EnumSet.allOf(ErrorMessages.class)
            .stream()
            .filter(errMsg -> errMsg.getErrCode().equalsIgnoreCase(errCode))
            .findFirst();
    }

    public String getExceptionMessage() {
        return Optional.ofNullable(getCustomMsg())
            .filter(Predicate.not(String::isBlank))
            .map(custom -> "%s, %s".formatted(userMsg, customMsg))
            .orElse(userMsg);
    }
}
