package com.kasperkiewicz.masters.imperative.server;

import com.kasperkiewicz.masters.common.Content;
import com.kasperkiewicz.masters.common.service.MetricsService;
import com.kasperkiewicz.masters.imperative.server.services.ContentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receiverImper")
public class RestApiController {

    private ContentService contentService;
    private MetricsService metricsService = new MetricsService();

    @Autowired
    public RestApiController(ContentService contentService ) {
        this.contentService = contentService;
    }


    @GetMapping()
    public List<Content> getWholeContent() {
        System.out.println("get whole content");
        return contentService.getContentList();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void addContent(@RequestBody Content content) {
        contentService.add(content);

        long endTimestamp = System.currentTimeMillis();
        long startTimestamp = content.getTimestamp();
        long handleTime = endTimestamp - startTimestamp;
        metricsService.updateForClient(content.getClientId(), handleTime);
        System.out.println("Content added");
    }

    @GetMapping("/metrics/average")
    public Map<String, Double> getMetricsAverage() {
        System.out.println("get summary content");
        return metricsService.getAverageHandleTimeForClients();
    }

    @GetMapping("/metrics/total")
    public Map<String, Long> getMetricsTotal() {
        System.out.println("get summary content");
        return metricsService.getTotalHandleTimeForClients();
    }
}
