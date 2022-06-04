package com.gmakris.matchmanager.service.crud.impl;

import reactor.core.scheduler.Scheduler;

public class AbstractCrudService {

    protected final Scheduler dbScheduler;

    public AbstractCrudService(
        final Scheduler dbScheduler
    ) {
        this.dbScheduler = dbScheduler;
    }
}
