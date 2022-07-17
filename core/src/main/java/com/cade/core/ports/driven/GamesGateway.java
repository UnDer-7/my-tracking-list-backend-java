package com.cade.core.ports.driven;

import com.cade.core.domain.Content;
import com.cade.core.domain.Page;
import io.smallrye.mutiny.Uni;

public interface GamesGateway {
    Uni<Page<Content>> search(Integer page, String searchArgs);
}
