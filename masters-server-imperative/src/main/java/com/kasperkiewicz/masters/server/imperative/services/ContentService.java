package com.kasperkiewicz.masters.server.imperative.services;

import com.kasperkiewicz.masters.common.Content;
import org.springframework.stereotype.Service;

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

    public List<Content> getContentList() {
        return contentList;
    }
}
