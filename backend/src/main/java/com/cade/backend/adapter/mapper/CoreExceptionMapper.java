package com.cade.backend.adapter.mapper;

import com.cade.api.dto.CoreExceptionDTO;
import com.cade.core.exception.CoreException;
import com.cade.core.utils.Constants;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface CoreExceptionMapper {

    CoreExceptionDTO toDTO(CoreException source);

}
