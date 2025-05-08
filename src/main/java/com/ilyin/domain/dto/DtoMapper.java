package com.ilyin.domain.dto;

import java.util.List;

public interface DtoMapper<E, D extends DtoMapper<E, D>> {

    E toEntity();

    D fromEntity(E entity);

    default List<D> fromEntityList(List<E> entities) {
        return entities.stream().map(this::fromEntity).toList();
    }
}
