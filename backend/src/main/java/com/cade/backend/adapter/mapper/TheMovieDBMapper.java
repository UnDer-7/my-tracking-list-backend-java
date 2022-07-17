package com.cade.backend.adapter.mapper;

import com.cade.backend.clients.themoviedb.dto.PageDTO;
import com.cade.backend.clients.themoviedb.dto.SearchResultDTO;
import com.cade.core.domain.Content;
import com.cade.core.domain.Page;
import com.cade.core.utils.Constants;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface TheMovieDBMapper {

    Page<Content> toDomain(PageDTO<SearchResultDTO> source);

    @AfterMapping
    default void afterToDomain(@MappingTarget final Page<Content> target, final PageDTO<SearchResultDTO> source) {
        target.setIsTheLastPage(source.getPage().equals(source.getTotalPages()));
    }
}
