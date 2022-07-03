package com.cade.backend.clients.themoviedb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchResultDTO {

    @JsonAlias({"release_date", "first_air_date"})
    private LocalDate releaseDate;
    private String overview;
    private String originalLanguage;
    private List<Integer> genreIds;
    private String posterPath;
    private List<String> originCountry;
    private String backdropPath;
    private Double popularity;
    private Double voteAverage;

    @JsonAlias({"original_title", "original_name"})
    private String originalTitle;

    @JsonAlias({"title", "name"})
    private String title;

    private Integer id;
    private Integer voteCount;

}