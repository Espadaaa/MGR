package com.kasperkiewicz.masters.server.imperative.services;

import com.google.common.collect.EvictingQueue;
import com.kasperkiewicz.masters.common.Content;
import java.util.Queue;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    private Queue<Content> evictingQueue;

    public ContentService() {

        this.evictingQueue = EvictingQueue.create(1000);
    }

    public void add(Content content){
        evictingQueue.add(content);
    }

    public Queue<Content> getContentList() {
        return evictingQueue;
    }
}
