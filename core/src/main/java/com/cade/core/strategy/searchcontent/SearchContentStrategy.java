package com.cade.core.strategy.searchcontent;

import com.cade.core.domain.Content;
import com.cade.core.domain.ContentType;
import io.smallrye.mutiny.Multi;

public interface SearchContentStrategy {

    Multi<Content> execute(String searchString);

    boolean accept(ContentType contentType);

}
