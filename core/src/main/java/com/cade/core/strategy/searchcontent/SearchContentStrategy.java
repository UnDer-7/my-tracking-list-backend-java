package com.cade.core.strategy.searchcontent;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import com.cade.core.domain.Page;
import io.smallrye.mutiny.Uni;

public interface SearchContentStrategy {

    Uni<Page<Content>> execute(Integer page, String searchString);

    boolean accept(ContentType contentType);

}
