package com.kasperkiewicz.masters.imperative.server.services;

import com.kasperkiewicz.masters.imperative.common.Content;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    private final List<Content> contentList;

    private static final Logger LOGGER = LogManager.getLogger(ContentService.class);

    public ContentService() {
        this.contentList = new ArrayList<>();
    }

    public void add(Content content) {
        contentList.add(content);
        LOGGER.info("Server added content");
    }

    public List<Content> getContentList() {
        LOGGER.info("Server get content list");
        return contentList;
    }
}
