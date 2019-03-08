package com.kasperkiewicz.masters.server.reactive.services;

import com.kasperkiewicz.masters.common.Content;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Queue;

@Service
public class ContentService {

    private Queue<Content> queue = new CircularFifoQueue<>();

    public synchronized void add(Content content) {
        queue.add(content);
    }

    public Flux<Content> getAllContent() {
        return Flux.fromIterable(queue);
    }
}
