package com.kasperkiewicz.masters.client;

import com.kasperkiewicz.masters.common.Content;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 6) {
            throw new RuntimeException("Expected 6 command line arguments");
        }

        String serverHost = args[0];
        int serverPort = Integer.parseInt(args[1]);
        String endpoint = args[2];
        int requestCount = Integer.parseInt(args[3]);
        String clientId = args[4];
        long startDate = Long.parseLong(args[5]);
        long waitDuration = startDate - System.currentTimeMillis();

        System.out.println("Simulation will start after " + waitDuration / 1000D + " seconds");
        HttpClientAdapter httpClientAdapter = new HttpClientAdapter(serverHost, serverPort, endpoint);
        Thread.sleep(waitDuration);
        System.out.println("Simulation started");
        for (int i = 0; i < requestCount; i++) {
            httpClientAdapter.addContent(() -> new Content(clientId, "test"));
        }
    }
}

