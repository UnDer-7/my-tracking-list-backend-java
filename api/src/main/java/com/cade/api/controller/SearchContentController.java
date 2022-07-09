package com.cade.api.controller;

import com.cade.api.dto.ContentDTO;
import com.cade.api.dto.ContentTypeDTO;
import com.cade.api.dto.PageDTO;
import io.smallrye.mutiny.Uni;

public interface SearchContentController {
    Uni<PageDTO<ContentDTO>> searchByType(String contentType, String searchArgs, final Integer page);
}
