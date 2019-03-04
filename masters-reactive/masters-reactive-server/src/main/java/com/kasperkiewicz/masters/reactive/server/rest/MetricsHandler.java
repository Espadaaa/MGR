package com.kasperkiewicz.masters.reactive.server.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MetricsHandler {

    public Mono<ServerResponse> getMetricsTotal(ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> getMetricsAverage(ServerRequest request) {
        return ServerResponse.ok().build();
    }
}
