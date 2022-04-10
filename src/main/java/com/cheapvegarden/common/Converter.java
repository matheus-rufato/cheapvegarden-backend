package com.cheapvegarden.common;

import java.util.List;

public interface Converter<Entity, Dto> {
    
    Dto toDto(Entity entity);

    Entity toEntity(Dto dto) throws IllegalAccessException;

    List<Dto> toDtoList(List<Entity> entityList);
}
