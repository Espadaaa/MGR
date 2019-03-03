package com.kasperkiewicz.masters.imperative.common;

import java.io.Serializable;

public class Content implements Serializable {


    private String content;
    private String clientId;
    private long timestamp;


    public Content(String clientId, String content) {
        this.clientId = clientId;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public Content() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getClientId() {
        return clientId;
    }
}
