package com.kasperkiewicz.masters.imperative.server;

import com.kasperkiewicz.masters.common.Content;
import com.kasperkiewicz.masters.imperative.server.services.ContentService;
import com.kasperkiewicz.masters.imperative.server.services.MetricsService;
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
    private MetricsService metricsService;

    @Autowired
    public RestApiController(ContentService contentService,
        MetricsService metricsService) {
        this.contentService = contentService;
        this.metricsService = metricsService;
    }


    @GetMapping()
    public List<Content> getWholeContent() {
        System.out.println("get whole content");
        return contentService.getContentList();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void addContent(@RequestBody Content content) {
        long endTimestamp = System.currentTimeMillis();
        long startTimestamp = content.getTimestamp();
        long handleTime = endTimestamp - startTimestamp;
        metricsService.updateForClient(content.getClientId(), handleTime);
        contentService.add(content);
    }

    @GetMapping("/metrics/clients/average")
    public Map<String, Double> getAverageHandleTimeForClients() {
        System.out.println("get metrics average per client");
        return metricsService.getAverageHandleTimeForClients();
    }

    @GetMapping("/metrics/clients/total")
    public Map<String, Long> getTotalHandleTimeForClients() {
        System.out.println("get metrics total per client");
        return metricsService.getTotalHandleTimeForClients();
    }

    @GetMapping("/metrics/global/total")
    public long getGlobalTotalHandleTime() {
        System.out.println("get metrics total for all");
        return metricsService.getGlobalTotalHandleTime();
    }

    @GetMapping("/metrics/global/average")
    public double getGlobalAverageHandleTime() {
        System.out.println("get metrics average for all");
        return metricsService.getGlobalAverageHandleTime();
    }

    @GetMapping("/metrics/global/request")
    public double getGlobalRequestCount() {
        System.out.println("get metrics average for all");
        return metricsService.getGlobalRequestCount();
    }
}
