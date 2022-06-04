package com.gmakris.matchmanager.service.crud;

import java.util.UUID;
import com.gmakris.matchmanager.entity.model.MatchOdds;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MatchOddsService {
    Mono<MatchOdds> findOne(UUID matchOddsId);

    Flux<MatchOdds> findAll();

    Mono<MatchOdds> create(MatchOdds matchOdds);

    Mono<MatchOdds> update(MatchOdds matchOdds);

    Mono<Void> delete(UUID matchOddsId);
}
