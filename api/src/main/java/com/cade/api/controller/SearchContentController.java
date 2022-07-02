package com.cade.api.controller;

import com.cade.api.dto.ContentDTO;
import com.cade.api.dto.ContentTypeDTO;
import io.smallrye.mutiny.Multi;

public interface SearchContentController {
    Multi<ContentDTO> searchByType(ContentTypeDTO contentType, String searchArgs);
}
