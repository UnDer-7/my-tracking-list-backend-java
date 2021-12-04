package com.cade.api.v1.tv;

import com.cade.api.v1.shared.PageDTO;
import com.cade.api.v1.shared.QueryDTO;
import io.smallrye.mutiny.Uni;

public interface TVResource {
    Uni<PageDTO<ResponseTVSearchDTO>> search(QueryDTO queryDTO);
}
