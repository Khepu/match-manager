package com.gmakris.matchmanager.service.crud;

import java.util.UUID;
import com.gmakris.matchmanager.entity.model.Match;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MatchService {
    Mono<Match> findOne(UUID matchId);

    Flux<Match> findAll();

    Mono<Match> create(Match match);

    Mono<Match> update(Match match);

    Mono<Match> delete(Match match);
}
