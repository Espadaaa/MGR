package com.kasperkiewicz.masters.server.imperative.services;

import com.kasperkiewicz.masters.common.Content;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


@Service
public class ContentService {

    private Queue<Content> queue = new CircularFifoQueue<>();

    public synchronized void add(Content content) {
        queue.add(content);
    }

    public List<Content> getAllContent() {
        return new ArrayList<>(queue);
    }
}
