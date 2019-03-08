package com.kasperkiewicz.masters.server.reactive.rest;

import com.kasperkiewicz.masters.common.Content;
import com.kasperkiewicz.masters.server.reactive.services.ContentService;
import com.kasperkiewicz.masters.server.reactive.services.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ContentHandler {

    private ContentService contentService;
    private MetricsService metricsService;

    @Autowired
    public ContentHandler(ContentService contentService,
                          MetricsService metricsService) {
        this.contentService = contentService;
        this.metricsService = metricsService;
    }

    public Mono<ServerResponse> getWholeContent(ServerRequest request) {
        return ServerResponse.ok().body(BodyInserters.fromPublisher(contentService.getContentList(), Content.class));

    }

    public Mono<ServerResponse> addContent(ServerRequest request) {
        return request
            .bodyToMono(Content.class)
            .doOnNext(contentService::add)
            .doOnNext(this::updateMetrics)
            .then(ServerResponse.ok().build());
    }

    private void updateMetrics(Content content) {
        long endTimestamp = System.currentTimeMillis();
        long startTimestamp = content.getTimestamp();
        long handleTime = endTimestamp - startTimestamp;
        metricsService.updateForClient(content.getClientId(), handleTime);
    }
}
