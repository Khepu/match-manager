package com.gmakris.matchmanager.service.crud.impl;

import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;
import static reactor.core.scheduler.Schedulers.parallel;

import java.util.UUID;
import com.gmakris.matchmanager.entity.model.Match;
import com.gmakris.matchmanager.repository.MatchRepository;
import com.gmakris.matchmanager.service.crud.MatchService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class MatchServiceImpl
    extends AbstractCrudService
    implements MatchService {

    private final MatchRepository repository;

    public MatchServiceImpl(
        final MatchRepository repository,
        @Qualifier("db-scheduler") final Scheduler dbScheduler
    ) {
        super(dbScheduler);

        this.repository = repository;
    }

    @Override
    public Mono<Match> findOne(final UUID matchId) {
        requireNonNull(matchId);

        return Mono
            .defer(() -> Mono
                .fromCallable(() -> repository.findOneById(matchId))
                .flatMap(Mono::justOrEmpty))
            .subscribeOn(dbScheduler)
            .publishOn(parallel());
    }

    @Override
    public Flux<Match> findAll() {
        return Flux
            .defer(() -> Mono
                .fromCallable(repository::findAll)
                .flatMapIterable(identity()))
            .subscribeOn(dbScheduler)
            .publishOn(parallel());
    }

    @Override
    public Mono<Match> create(final Match match) {
        return Mono
            .defer(() -> Mono
                .fromRunnable(() -> repository.save(match))
                .thenReturn(match))
            .subscribeOn(dbScheduler)
            .publishOn(parallel());
    }

    @Override
    public Mono<Match> update(final Match match) {
        return Mono
            .defer(() -> Mono
                .fromCallable(() -> repository.findOneById(match.getId()))
                .flatMap(__ -> Mono
                    .fromCallable(() -> repository.save(match))
                    .thenReturn(match)))
                .subscribeOn(dbScheduler)
                .publishOn(parallel());
    }

    @Override
    public Mono<Void> delete(final UUID matchId) {
        return Mono
            .defer(() -> Mono
                .fromRunnable(() -> repository.deleteById(matchId))
                .then())
            .subscribeOn(dbScheduler)
            .publishOn(parallel());
    }
}
