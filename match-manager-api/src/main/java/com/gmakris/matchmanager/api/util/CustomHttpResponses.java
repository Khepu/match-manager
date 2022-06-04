package com.gmakris.matchmanager.api.util;

import static org.springframework.web.reactive.function.server.ServerResponse.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class CustomHttpResponses {

    public static Mono<ServerResponse> internalServerError() {
        return status(HttpStatus.INTERNAL_SERVER_ERROR)
            .build();
    }
}
