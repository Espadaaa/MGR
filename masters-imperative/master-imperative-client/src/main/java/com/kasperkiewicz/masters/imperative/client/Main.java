package com.kasperkiewicz.masters.imperative.client;

import com.kasperkiewicz.masters.imperative.common.Content;
import com.kasperkiewicz.masters.imperative.client.service.HttpClientAdapter;
public class Main {

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 5) {
            throw new RuntimeException("Expected 5 command line arguments");
        }

        String serverHost = args[0];
        int serverPort = Integer.parseInt(args[1]);
        int requestCount = Integer.parseInt(args[2]);
        String clientId = args[3];
        long startDate = Long.parseLong(args[4]);
        long waitDuration = startDate - System.currentTimeMillis();

        System.out.println("Simulation will start after " + waitDuration / 1000D + " seconds");
        HttpClientAdapter httpClientAdapter = new HttpClientAdapter(serverHost, serverPort);
        Thread.sleep(waitDuration);
        System.out.println("Simulation started");
        for (int i = 0; i < requestCount; i++) {
            httpClientAdapter.addContent(() -> new Content(clientId, "test"));
        }
    }
}

