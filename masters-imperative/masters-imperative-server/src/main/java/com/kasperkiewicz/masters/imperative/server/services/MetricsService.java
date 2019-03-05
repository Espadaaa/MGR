package com.kasperkiewicz.masters.imperative.server.services;

import static java.util.stream.Collectors.toMap;

import com.kasperkiewicz.masters.common.Metrics;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private Metrics globalMetrics = new Metrics();
    private Map<String, Metrics> clientMetrics = new HashMap<>();

    public void updateForClient(String clientId, long handleTime) {
        Metrics metrics = clientMetrics.computeIfAbsent(clientId, m -> new Metrics());
        metrics.update(handleTime);
        globalMetrics.update(handleTime);
    }

    public long getGlobalTotalHandleTime() {
        return globalMetrics.getTotalHandleTime();
    }

    public double getGlobalAverageHandleTime() {
        return globalMetrics.getAverageHandleTime();
    }

    public int getGlobalRequestCount() {
        return globalMetrics.getRequestCount();
    }

    public Map<String, Long> getTotalHandleTimeForClients() {
        return mapClientMetrics(clientMetrics, Metrics::getTotalHandleTime);
    }

    public Map<String, Double> getAverageHandleTimeForClients() {
        return mapClientMetrics(clientMetrics, Metrics::getAverageHandleTime);
    }

    private <T> Map<String, T> mapClientMetrics(Map<String, Metrics> clientMetrics,
        Function<Metrics, T> valueMapper) {
        return clientMetrics
            .entrySet()
            .stream()
            .collect(toMap(Entry::getKey, e -> valueMapper.apply(e.getValue())));
    }
}

