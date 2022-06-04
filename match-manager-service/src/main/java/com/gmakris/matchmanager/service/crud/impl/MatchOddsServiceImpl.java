package com.gmakris.matchmanager.service.crud.impl;

import static java.util.Objects.requireNonNull;
import static java.util.function.UnaryOperator.identity;
import static reactor.core.scheduler.Schedulers.parallel;

import java.util.UUID;
import com.gmakris.matchmanager.entity.model.MatchOdds;
import com.gmakris.matchmanager.repository.MatchOddsRepository;
import com.gmakris.matchmanager.service.crud.MatchOddsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class MatchOddsServiceImpl
    extends AbstractCrudService
    implements MatchOddsService {

    private final MatchOddsRepository repository;

    public MatchOddsServiceImpl(
        final MatchOddsRepository repository,
        @Qualifier("db-scheduler") final Scheduler dbScheduler
    ) {
        super(dbScheduler);
        this.repository = repository;
    }

    @Override
    public Mono<MatchOdds> findOne(final UUID matchOddsId) {
        requireNonNull(matchOddsId);

        return Mono
            .defer(() -> Mono
                .fromCallable(() -> repository.findOneById(matchOddsId))
                .flatMap(Mono::justOrEmpty))
            .subscribeOn(dbScheduler)
            .publishOn(parallel());
    }

    @Override
    public Flux<MatchOdds> findAll() {
        return Flux
            .defer(() -> Mono
                .fromCallable(repository::findAll)
                .flatMapIterable(identity()))
            .subscribeOn(dbScheduler)
            .publishOn(parallel());
    }

    @Override
    public Mono<MatchOdds> create(final MatchOdds matchOdds) {
        return Mono
            .defer(() -> Mono
                .fromRunnable(() -> repository.save(matchOdds))
                .thenReturn(matchOdds))
            .subscribeOn(dbScheduler)
            .publishOn(parallel());
    }

    @Override
    public Mono<MatchOdds> update(final MatchOdds matchOdds) {
        return Mono
            .defer(() -> Mono
                .fromCallable(() -> repository.findOneById(matchOdds.getId()))
                .flatMap(__ -> Mono
                    .fromCallable(() -> repository.save(matchOdds))
                    .thenReturn(matchOdds)))
            .subscribeOn(dbScheduler)
            .publishOn(parallel());
    }

    @Override
    public Mono<Void> delete(final UUID matchOddsId) {
        return Mono
            .defer(() -> Mono
                .fromRunnable(() -> repository.deleteById(matchOddsId))
                .then())
            .subscribeOn(dbScheduler)
            .publishOn(parallel());
    }
}
