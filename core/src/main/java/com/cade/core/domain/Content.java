package com.cade.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
public class Content {
    private Long id;
    private LocalDate releaseDate;
    private String overview;
    private String originalLanguage;
    private String posterPath;
    private List<String> originCountry;
    private String backdropPath;
    private String originalTitle;
    private String title;

    public void concatPosterPathUrl(final String url) {
        Objects.requireNonNull(url, "PostUrl cannot be null");

        if (Objects.nonNull(posterPath)) {
            posterPath = "%s%s".formatted(url, posterPath);
        }
    }

    public void concatBackdropPathUrl(final String url) {
        Objects.requireNonNull(url, "BackdropUrl cannot be null");

        if (Objects.nonNull(backdropPath)) {
            backdropPath = "%s%s".formatted(url, backdropPath);
        }
    }
}
