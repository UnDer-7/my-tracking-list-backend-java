package com.cade.backend.adapter.mapper;

import com.cade.api.dto.ContentTypeDTO;
import com.cade.core.domain.ContentType;
import com.cade.core.utils.Constants;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface ContentTypeMapper extends MainMapper<ContentType, ContentTypeDTO> {
}
