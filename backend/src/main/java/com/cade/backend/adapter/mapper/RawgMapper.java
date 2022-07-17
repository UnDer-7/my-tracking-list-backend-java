package com.cade.backend.adapter.mapper;

import com.cade.backend.clients.rawg.dto.PageDTO;
import com.cade.backend.clients.rawg.dto.SearchResultDTO;
import com.cade.core.domain.Content;
import com.cade.core.domain.Page;
import com.cade.core.utils.Constants;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface RawgMapper {

    default Page<Content> toDomain(final Integer pageNumber, final PageDTO<SearchResultDTO> source) {
        final var page = new Page<Content>();
        final var results = source.getResults()
            .stream()
            .map(dto -> Content.builder()
                .id(dto.getId())
                .releaseDate(LocalDate.parse(dto.getReleased(), DateTimeFormatter.ISO_DATE))
                .overview(null)
                .originalLanguage(null)
                .posterPath(dto.getBackgroundImage())
                .originCountry(Collections.emptyList())
                .originalTitle(null)
                .title(dto.getName())
                .build())
            .toList();

        page.setPage(pageNumber.longValue());
        page.setTotalResults(source.getCount());
        page.setIsTheLastPage(Objects.isNull(source.getNext()));
        page.setResults(results);

        return page;
    }

}
