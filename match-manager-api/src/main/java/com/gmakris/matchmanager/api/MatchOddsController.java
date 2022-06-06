package com.gmakris.matchmanager.api;

import static com.gmakris.matchmanager.api.util.CustomHttpResponses.badRequest;
import static com.gmakris.matchmanager.api.util.CustomHttpResponses.internalServerError;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PATCH;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.UUID;
import com.gmakris.matchmanager.dto.MatchOddsDto;
import com.gmakris.matchmanager.mapper.MatchOddsMapper;
import com.gmakris.matchmanager.service.crud.MatchOddsService;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Slf4j
@Component
public class MatchOddsController
    implements Controller {

    private final MatchOddsMapper matchOddsMapper;
    private final MatchOddsService matchOddsService;

    public MatchOddsController(
        final MatchOddsMapper matchOddsMapper,
        final MatchOddsService matchOddsService
    ) {
        this.matchOddsMapper = matchOddsMapper;
        this.matchOddsService = matchOddsService;
    }

    private Mono<ServerResponse> ok(final Publisher<MatchOddsDto> matchOddsDtos) {
        requireNonNull(matchOddsDtos);

        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(matchOddsDtos, MatchOddsDto.class);
    }

    @NonNull
    private Mono<ServerResponse> findAll(final ServerRequest serverRequest) {
        return Mono.just(
                matchOddsService.findAll()
                    .map(matchOddsMapper::to))
            .flatMap(this::ok)
            .doOnError(throwable -> log
                .error("Unhandled error for server-request '{}'!",
                    serverRequest,
                    throwable))
            .onErrorResume(throwable -> internalServerError());
    }

    @NonNull
    private Mono<ServerResponse> findOne(final ServerRequest serverRequest) {
        return Mono.justOrEmpty(serverRequest.pathVariable("id"))
            .map(UUID::fromString)
            .flatMap(matchOddsService::findOne)
            .map(matchOddsMapper::to)
            .transform(this::ok)
            .doOnError(throwable -> log
                .error("Unhandled error for server-request '{}'!",
                    serverRequest,
                    throwable))
            .onErrorResume(throwable -> internalServerError());
    }

    @NonNull
    private Mono<ServerResponse> create(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MatchOddsDto.class)
            .map(matchOddsMapper::from)
            .flatMap(matchOddsService::create)
            .map(matchOddsMapper::to)
            .transform(this::ok)
            .doOnError(throwable -> log
                .error("Unhandled error for server-request '{}'!",
                    serverRequest,
                    throwable))
            .onErrorResume(throwable -> internalServerError());
    }

    @NonNull
    private Mono<ServerResponse> update(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MatchOddsDto.class)
            .map(matchOddsMapper::from)
            .flatMap(matchOddsService::update)
            .map(matchOddsMapper::to)
            .flatMap(matchOdds -> Mono.just(matchOdds)
                .transform(this::ok))
            .doOnError(throwable -> log
                .error("Unhandled error for server-request '{}'!",
                    serverRequest,
                    throwable))
            .onErrorResume(throwable -> internalServerError())
            .switchIfEmpty(badRequest());
    }

    @NonNull
    private Mono<ServerResponse> delete(final ServerRequest serverRequest) {
        return Mono.justOrEmpty(serverRequest.pathVariable("id"))
            .map(UUID::fromString)
            .flatMap(matchOddsService::delete)
            .flatMap(__ -> ServerResponse
                .ok()
                .build())
            .doOnError(throwable -> log
                .error("Unhandled error for server-request '{}'!",
                    serverRequest,
                    throwable))
            .onErrorResume(throwable -> internalServerError());
    }

    @Override
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/match/"), this::findAll)
            .and(route(GET("/match/{id}"), this::findOne))
            .and(route(PUT("/match/"), this::create))
            .and(route(PATCH("/match/"), this::update))
            .and(route(DELETE("/match/"), this::delete));
    }
}
