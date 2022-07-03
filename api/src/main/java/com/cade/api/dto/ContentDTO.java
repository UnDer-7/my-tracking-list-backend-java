package com.cade.api.dto;

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
public class ContentDTO {
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
