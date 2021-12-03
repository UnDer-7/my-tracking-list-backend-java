package com.cade.core.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@UtilityClass
public class OptionalHelper {
    public <P> P orElseNull(final Optional<P> value) {
        return value.orElse(null);
    }
}
