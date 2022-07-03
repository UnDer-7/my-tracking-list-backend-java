package com.cade.backend.adapter.mapper;

import com.cade.api.dto.ContentDTO;
import com.cade.backend.clients.themoviedb.dto.PageDTO;
import com.cade.backend.clients.themoviedb.dto.SearchResultDTO;
import com.cade.core.domain.Content;
import com.cade.core.domain.Page;
import com.cade.core.utils.Constants;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface ContentMapper {
    com.cade.api.dto.PageDTO<ContentDTO> toDTO(Page<Content> source);

    Page<Content> toDomain(PageDTO<SearchResultDTO> source);
}
