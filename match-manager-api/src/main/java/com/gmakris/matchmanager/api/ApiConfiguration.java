package com.gmakris.matchmanager.api;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@ComponentScan
@Configuration
public class ApiConfiguration {

    @Bean("api-routes")
    public RouterFunction<ServerResponse> routes(
        final List<Controller> controllers
    ) {
        log.info("Discovered {} controllers.", controllers.size());

        return controllers
            .stream()
            .map(Controller::routes)
            .reduce(RouterFunction::and)
            .orElseThrow(() -> new RuntimeException(
                "Application failed to start. No controllers discovered!"));
    }
}
