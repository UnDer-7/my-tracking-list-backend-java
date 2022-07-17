package com.cade.api.dto;

import java.util.Optional;
import java.util.function.Predicate;

public enum ContentTypeDTO {
    TV,
    MOVIE,
    GAME;

    public static ContentTypeDTO fromString(final String value) {
        return Optional.ofNullable(value)
            .filter(Predicate.not(String::isBlank))
            .map(str -> {
                if (ContentTypeDTO.TV.name().equalsIgnoreCase(str) || "tvs".equalsIgnoreCase(str)) return ContentTypeDTO.TV;
                if (ContentTypeDTO.MOVIE.name().equalsIgnoreCase(str) || "movies".equalsIgnoreCase(str)) return ContentTypeDTO.MOVIE;
                if (ContentTypeDTO.GAME.name().equalsIgnoreCase(str) || "games".equalsIgnoreCase(str)) return ContentTypeDTO.GAME;
                return null;
            })
            .orElse(null);
    }
}
