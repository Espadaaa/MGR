package com.kasperkiewicz.masters.server.reactive.services;

import com.kasperkiewicz.masters.common.Content;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {

    private final List<Content> contentList;

    public ContentService() {
        this.contentList = new ArrayList<>();
    }

    public void add(Content content) {
        try {
            contentList.add(content);
            Thread.sleep(1000);
            System.out.println("Content added");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Flux<Content> getContentList() {
        return Flux.fromIterable(contentList);
    }
}
