package com.gmakris.matchmanager.service.crud.impl;

import com.gmakris.matchmanager.entity.model.AbstractEntity;
import reactor.core.scheduler.Scheduler;

public class AbstractCrudService<Entity extends AbstractEntity> {

    protected final Scheduler dbScheduler;

    public AbstractCrudService(
        final Scheduler dbScheduler
    ) {
        this.dbScheduler = dbScheduler;
    }
}
