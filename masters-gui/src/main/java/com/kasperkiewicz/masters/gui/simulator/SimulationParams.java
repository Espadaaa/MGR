package com.kasperkiewicz.masters.gui.simulator;

public class SimulationParams {

    private int serverPort;
    private String endpoint;
    private int clientCount;
    private int messageCount;
    private long startTime;

    public SimulationParams(int serverPort, String endpoint, int clientCount, int messageCount, long startTime) {
        this.serverPort = serverPort;
        this.endpoint = endpoint;
        this.clientCount = clientCount;
        this.messageCount = messageCount;
        this.startTime = startTime;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public int getClientCount() {
        return clientCount;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public long getStartTime() {
        return startTime;
    }
}
