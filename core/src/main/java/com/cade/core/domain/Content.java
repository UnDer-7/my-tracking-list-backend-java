package com.cade.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class Content {
    private Integer id;
    private LocalDate releaseDate;
    private String overview;
    private String originalLanguage;
    private String posterPath;
    private List<String> originCountry;
    private String backdropPath;
    private String originalTitle;
    private String title;
}
