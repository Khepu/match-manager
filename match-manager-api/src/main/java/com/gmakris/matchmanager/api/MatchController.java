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
import com.gmakris.matchmanager.dto.MatchDto;
import com.gmakris.matchmanager.mapper.MatchMapper;
import com.gmakris.matchmanager.service.crud.MatchService;
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
public class MatchController
    implements Controller {

    private final MatchMapper matchMapper;
    private final MatchService matchService;

    public MatchController(
        final MatchMapper matchMapper,
        final MatchService matchService
    ) {
        this.matchMapper = matchMapper;
        this.matchService = matchService;
    }

    private Mono<ServerResponse> ok(final Publisher<MatchDto> matchDtos) {
        requireNonNull(matchDtos);

        return ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(matchDtos, MatchDto.class);
    }

    @NonNull
    private Mono<ServerResponse> findAll(final ServerRequest serverRequest) {
        return Mono.just(
                matchService.findAll()
                    .map(matchMapper::to))
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
            .flatMap(matchService::findOne)
            .map(matchMapper::to)
            .transform(this::ok)
            .doOnError(throwable -> log
                .error("Unhandled error for server-request '{}'!",
                    serverRequest,
                    throwable))
            .onErrorResume(throwable -> internalServerError());
    }

    @NonNull
    private Mono<ServerResponse> create(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MatchDto.class)
            .map(matchMapper::from)
            .flatMap(matchService::create)
            .map(matchMapper::to)
            .transform(this::ok)
            .doOnError(throwable -> log
                .error("Unhandled error for server-request '{}'!",
                    serverRequest,
                    throwable))
            .onErrorResume(throwable -> internalServerError());
    }

    @NonNull
    private Mono<ServerResponse> update(final ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MatchDto.class)
            .map(matchMapper::from)
            .flatMap(matchService::update)
            .map(matchMapper::to)
            .flatMap(match -> Mono.just(match)
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
            .flatMap(matchService::delete)
            .flatMap(__ -> ServerResponse
                .ok()
                .build())
            .doOnError(throwable -> log
                .error("Unhandled error for server-request '{}'!",
                    serverRequest,
                    throwable))
            .onErrorResume(throwable -> internalServerError())
            .switchIfEmpty(badRequest());
    }

    @Override
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/match/"), this::findAll)
            .and(route(GET("/match/{id}"), this::findOne))
            .and(route(PUT("/match/"), this::create))
            .and(route(PATCH("/match/"), this::update))
            .and(route(DELETE("/match/{id}"), this::delete));
    }
}
