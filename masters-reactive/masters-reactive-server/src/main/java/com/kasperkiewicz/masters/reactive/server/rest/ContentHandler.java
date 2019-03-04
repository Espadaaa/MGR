package com.kasperkiewicz.masters.reactive.server.rest;

import com.google.gson.Gson;
import com.kasperkiewicz.masters.common.Content;
import com.kasperkiewicz.masters.reactive.server.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ContentHandler {

    private Gson gson = new Gson();
    private ContentService contentService;

    @Autowired
    public ContentHandler(ContentService contentService) {
        this.contentService = contentService;
    }

    public Mono<ServerResponse> getWholeContent(ServerRequest request) {
        System.out.println("get whole content react");
        return ServerResponse.ok().body(BodyInserters.fromPublisher(contentService.getContentList(), Content.class));

    }

    public Mono<ServerResponse> addContent(ServerRequest request) {
        System.out.println("post whole content react");
        return request
            .bodyToFlux(String.class)
            .map(str -> gson.fromJson(str, Content.class))
            .doOnNext(contentService::add)
            .then(ServerResponse.ok().build());
    }
}
