package com.cade.features.tv;

import com.cade.shared.clients.themoviedb.dto.ResponsePageDTO;
import com.cade.shared.clients.themoviedb.dto.ResponseSearchDTO;
import com.cade.shared.dtos.PageDTO;
import com.cade.features.tv.dto.ResponseTVSearchDTO;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "cdi",
    builder = @Builder(disableBuilder = true),
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface TVMapper {
    @Mapping(source = "page", target = "pageIndex")
    PageDTO<ResponseTVSearchDTO> toPageDTO(ResponsePageDTO<ResponseSearchDTO> dto);
}
