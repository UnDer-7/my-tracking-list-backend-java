package com.cade.core.ports.driver;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import com.cade.core.domain.Page;
import io.smallrye.mutiny.Uni;

public interface SearchContentService {
    Uni<Page<Content>> searchByContent(ContentType contentType, final Integer page, String searchArgs);
}
