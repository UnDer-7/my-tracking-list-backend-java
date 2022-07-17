package com.cade.backend.adapter.mapper;

import com.cade.api.dto.ContentDTO;
import com.cade.api.dto.PageDTO;
import com.cade.core.domain.Content;
import com.cade.core.domain.Page;
import com.cade.core.utils.Constants;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface ContentMapper {

    PageDTO<ContentDTO> toDTO(Page<Content> source);

}
