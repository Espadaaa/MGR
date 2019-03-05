package com.kasperkiewicz.masters.server.imperative.rest;

import com.kasperkiewicz.masters.common.Content;
import com.kasperkiewicz.masters.server.imperative.services.ContentService;
import com.kasperkiewicz.masters.server.imperative.services.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receiverImper")
public class ContentController {

    private ContentService contentService;
    private MetricsService metricsService;

    @Autowired
    public ContentController(ContentService contentService,
                             MetricsService metricsService) {
        this.contentService = contentService;
        this.metricsService = metricsService;
    }

    @GetMapping()
    public List<Content> getWholeContent() {
        return contentService.getContentList();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void addContent(@RequestBody Content content) {
        System.out.println("Adding new content");
        long endTimestamp = System.currentTimeMillis();
        long startTimestamp = content.getTimestamp();
        long handleTime = endTimestamp - startTimestamp;
        metricsService.updateForClient(content.getClientId(), handleTime);
        contentService.add(content);
    }
}
