package com.kasperkiewicz.masters.server.reactive.rest;

import com.kasperkiewicz.masters.server.reactive.services.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class MetricsHandler {

    private MetricsService metricsService;

    @Autowired
    public MetricsHandler(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    public Mono<ServerResponse> globalTotalHandleTime(ServerRequest request) {
        return ServerResponse.ok()
            .body(BodyInserters.fromObject(metricsService.getGlobalTotalHandleTime()));
    }

    public Mono<ServerResponse> globalAverageHandleTime(ServerRequest request) {
        return ServerResponse.ok()
            .body(BodyInserters.fromObject(metricsService.getGlobalAverageHandleTime()));
    }

    public Mono<ServerResponse> globalRequestCount(ServerRequest request) {
        return ServerResponse.ok()
            .body(BodyInserters.fromObject(metricsService.getGlobalRequestCount()));
    }

    public Mono<ServerResponse> getTotalHandleTimeForClients(ServerRequest request) {
        return ServerResponse.ok()
            .body(BodyInserters.fromObject(metricsService.getTotalHandleTimeForClients()));
    }

    public Mono<ServerResponse> averageHandleTimeForClients(ServerRequest request) {
        return ServerResponse.ok()
            .body(BodyInserters.fromObject(metricsService.getAverageHandleTimeForClients()));
    }
}
