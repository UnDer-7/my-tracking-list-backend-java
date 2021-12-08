package com.cade.features.tv;

import com.cade.exceptions.BusinessException;
import com.cade.exceptions.ErrorMessages;
import com.cade.features.tv.dto.ResponseTVSearchDTO;
import com.cade.features.user.User;
import com.cade.shared.clients.themoviedb.TheMovieDBSearchRestRepository;
import com.cade.shared.dtos.PageDTO;
import com.cade.shared.dtos.QueryDTO;
import com.cade.features.user.auth.AuthService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor(onConstructor_={@Inject})
public class TVService {
    private final TheMovieDBSearchRestRepository restRepository;
    private final AuthService authService;
    private final TVMapper tvMapper;

    public Uni<PageDTO<ResponseTVSearchDTO>> search(final QueryDTO query) {
        return authService.getCurrentUser()
            .onItem().ifNull()
            .failWith(new BusinessException(ErrorMessages.USER_ALREADY_REGISTERED))
            .map(User::getLocale)
            .flatMap(locale -> restRepository.searchTV(query.getQuery(), query.getPageIndex(), locale))
            .map(tvMapper::toPageDTO);
    }
}
