package com.cade.backend.config;

import com.cade.core.domain.ErrorMessages;
import io.quarkus.runtime.StartupEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class AppLifecycle {

    void onStartup(@Observes StartupEvent event) {
       validateErrorMessages();
       validateIfHasErrorMessagesWithoutUserMsg();
    }

    void validateErrorMessages() {
        final var errorMessagesList = Arrays.asList(ErrorMessages.values());
        final var errorCodesList = errorMessagesList.stream().map(ErrorMessages::getErrCode).toList();

        final var errorsDuplicated = errorMessagesList.stream()
            .map(ErrorMessages::getErrCode)
            .filter(errCode -> Collections.frequency(errorCodesList, errCode) > 1)
            .collect(Collectors.toSet());

        if (!errorsDuplicated.isEmpty()) {
            log.error("There is duplicated errorCodes on ErrorMessages Enum. Duplicated Code=[{}]", String.join(", ", errorsDuplicated));
        }
    }

    void validateIfHasErrorMessagesWithoutUserMsg() {
        final var messagesWithoutUserMsg = EnumSet.allOf(ErrorMessages.class)
            .stream()
            .filter(errMsg -> Objects.isNull(errMsg.getUserMsg()) || errMsg.getUserMsg().isBlank())
            .map(Enum::name)
            .collect(Collectors.joining(", "));

        if (!messagesWithoutUserMsg.isBlank()) {
            log.error("There is ErrorMessages Enum with blank or null userMsg. Invalid ErrorMessages=[{}]", messagesWithoutUserMsg);
        }
    }
}
