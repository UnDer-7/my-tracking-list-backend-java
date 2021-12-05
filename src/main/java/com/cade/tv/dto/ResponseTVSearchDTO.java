package com.cade.tv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTVSearchDTO {
    private String backdropUrl;
    private String firstAirDate;
    private List<Long> genreIds;
    private Long id;
    private String name;
    private List<String> originCountry;
    private String originalLanguage;
    private String originalName;
    private String overview;
    private Double popularity;
    private String posterUrl;
    private Double voteAverage;
    private Double voteCount;
}
