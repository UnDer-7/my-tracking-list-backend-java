package com.cade.core.ports.driver;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import io.smallrye.mutiny.Multi;

public interface SearchContentService {
    Multi<Content> searchByContent(ContentType contentType, String searchArgs);
}
