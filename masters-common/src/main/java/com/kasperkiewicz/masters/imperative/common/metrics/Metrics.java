package com.kasperkiewicz.masters.imperative.common.metrics;

public class Metrics {

    private double averageHandleTime;
    private long totalHandleTime;
    private int requestCount;

    public void update(long handleTime) {
        requestCount++;
        totalHandleTime += handleTime;
        averageHandleTime = totalHandleTime / (double) requestCount;
    }

    public double getAverageHandleTime() {
        return averageHandleTime;
    }

    public long getTotalHandleTime() {
        return totalHandleTime;
    }

    public int getRequestCount() {
        return requestCount;
    }
}
