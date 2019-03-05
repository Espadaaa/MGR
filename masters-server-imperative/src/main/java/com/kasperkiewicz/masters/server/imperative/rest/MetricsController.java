package com.kasperkiewicz.masters.server.imperative.rest;

import com.kasperkiewicz.masters.server.imperative.services.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private MetricsService metricsService;

    @Autowired
    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/clients/average")
    public Map<String, Double> getAverageHandleTimeForClients() {
        return metricsService.getAverageHandleTimeForClients();
    }

    @GetMapping("/clients/total")
    public Map<String, Long> getTotalHandleTimeForClients() {
        return metricsService.getTotalHandleTimeForClients();
    }

    @GetMapping("/global/total")
    public long getGlobalTotalHandleTime() {
        return metricsService.getGlobalTotalHandleTime();
    }

    @GetMapping("/global/average")
    public double getGlobalAverageHandleTime() {
        return metricsService.getGlobalAverageHandleTime();
    }

    @GetMapping("/global/request")
    public double getGlobalRequestCount() {
        return metricsService.getGlobalRequestCount();
    }
}
