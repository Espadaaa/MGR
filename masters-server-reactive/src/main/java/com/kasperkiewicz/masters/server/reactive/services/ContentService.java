package com.kasperkiewicz.masters.server.reactive.services;

import com.google.common.collect.EvictingQueue;
import com.kasperkiewicz.masters.common.Content;
import java.util.Queue;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ContentService {

    private Queue<Content> evictingQueue;

    public ContentService() {

        this.evictingQueue = EvictingQueue.create(1000);
    }

    public void add(Content content) {
        evictingQueue.add(content);
        System.out.println("Content added");
    }

    public Flux<Content> getContentList() {
        return Flux.fromIterable(evictingQueue);
    }
}
