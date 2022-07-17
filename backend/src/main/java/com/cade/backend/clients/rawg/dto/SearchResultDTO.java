package com.cade.backend.clients.rawg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchResultDTO {

    private Long added;
    private Double rating;
    private Long metacritic;
    private Long playtime;
    private List<ShortScreenshotsItemDTO> shortScreenshots;
    private List<PlatformsItemDTO> platforms;
    private String score;
    private Long ratingTop;
    private Long reviewsTextCount;
    private List<RatingItemDTO> ratings;
    private List<GenresItemDTO> genres;
    private String saturatedColor;
    private AddedByStatusDTO addedByStatus;
    private Long id;
    private List<ParentPlatformsItemDTO> parentPlatforms;
    private Long ratingsCount;
    private String slug;
    private String released;
    private List<StoresItemDTO> stores;
    private Long suggestionsCount;
    private List<TagsItemDTO> tags;
    private String backgroundImage;
    private Boolean tba;
    private String dominantColor;
    private EsrbRatingDTO esrbRating;
    private String name;
    private Long communityRating;
    private String updated;
    private Long reviewsCount;

}