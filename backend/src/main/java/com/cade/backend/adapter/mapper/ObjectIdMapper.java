package com.cade.backend.adapter.mapper;

import com.cade.core.utils.Constants;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface ObjectIdMapper {
    default String fromObjectId(final ObjectId source) {
        return Optional.ofNullable(source)
            .map(ObjectId::toHexString)
            .orElse(null);
    }

    default ObjectId toObjectId(final String source) {
        return Optional.ofNullable(source)
            .map(ObjectId::new)
            .orElse(null);
    }
}
