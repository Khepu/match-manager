package com.gmakris.matchmanager.api;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@FunctionalInterface
public interface Controller {

    /**
     * @return a composition of all the routes available in the controller.
     */
    RouterFunction<ServerResponse> routes();
}
