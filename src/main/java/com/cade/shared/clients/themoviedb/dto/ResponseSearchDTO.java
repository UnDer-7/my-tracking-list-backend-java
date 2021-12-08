package com.cade.shared.clients.themoviedb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseSearchDTO {
    private String backdropPath;
    private String firstAirDate;
    private List<Long> genreIds;
    private Long id;
    private String name;
    private List<String> originCountry;
    private String originalLanguage;
    private String originalName;
    private String overview;
    private Double popularity;
    private String posterPath;
    private Double voteAverage;
    private Double voteCount;
}
