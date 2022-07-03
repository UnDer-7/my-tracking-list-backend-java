package com.cade.backend.adapter.rest;

import com.cade.api.controller.SearchContentController;
import com.cade.api.dto.ContentDTO;
import com.cade.api.dto.ContentTypeDTO;
import com.cade.api.dto.PageDTO;
import com.cade.backend.adapter.mapper.ContentMapper;
import com.cade.backend.adapter.mapper.ContentTypeMapper;
import com.cade.core.ports.driver.SearchContentService;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Slf4j
//@Authenticated
@Path("/search-content")
@ApplicationScoped
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchContentControllerImpl implements SearchContentController {
    private final ContentTypeMapper contentTypeMapper;
    private final ContentMapper contentMapper;
    private final SearchContentService searchContentService;

    @GET
    @Override
    @Path("/{contentType}")
    public Uni<PageDTO<ContentDTO>> searchByType(
        @PathParam("contentType") final ContentTypeDTO contentTypeDTO,
        @QueryParam("search-args") final String searchArgs,
        @QueryParam("page") final Integer page) {

        final var contentType = contentTypeMapper.toDomain(contentTypeDTO);

        return searchContentService.searchByContent(contentType, page, searchArgs).map(contentMapper::toDTO);
    }

}
