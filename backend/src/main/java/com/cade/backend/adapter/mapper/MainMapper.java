package com.cade.backend.adapter.mapper;

import java.util.Collection;

public interface MainMapper<E, D> {

    E toDomain(D dto);

    Collection<E> toDomain(Collection<D> dtos);

    D toDTO(E domain);

    Collection<D> toDTO(Collection<E> domains);

}
