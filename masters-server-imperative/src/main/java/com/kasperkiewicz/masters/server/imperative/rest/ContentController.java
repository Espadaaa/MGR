package com.kasperkiewicz.masters.server.imperative.rest;

import com.kasperkiewicz.masters.common.Content;
import com.kasperkiewicz.masters.server.imperative.services.ContentService;
import com.kasperkiewicz.masters.server.imperative.services.MetricsService;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Queue<Content> getWholeContent() {
        return contentService.getContentList();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void addContent(@RequestBody Content content) throws InterruptedException {

        updateMetrics(content);
        contentService.add(content);
    }
    private void updateMetrics(Content content) {
        long endTimestamp = System.currentTimeMillis();
        long startTimestamp = content.getTimestamp();
        long handleTime = endTimestamp - startTimestamp;
        metricsService.updateForClient(content.getClientId(), handleTime);
    }
}
