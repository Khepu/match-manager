package com.gmakris.matchmanager.mapper;

import com.gmakris.matchmanager.entity.model.AbstractEntity;

public interface GenericMapper<Entity extends AbstractEntity, Dto> {

    Entity from(Dto dto);

    Dto to(Entity entity);
}
