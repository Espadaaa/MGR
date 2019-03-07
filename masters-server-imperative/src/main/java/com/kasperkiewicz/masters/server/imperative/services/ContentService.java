package com.kasperkiewicz.masters.server.imperative.services;

import com.kasperkiewicz.masters.common.Content;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    private final List<Content> contentList;

    public ContentService() {
        this.contentList = new ArrayList<>();
    }

    public void add(Content content) {
        contentList.add(content);
        System.out.println("Content added");
    }

    public List<Content> getContentList() {
        return contentList;
    }
}
