package com.kasperkiewicz.masters.imperative.client.service;

import com.google.gson.Gson;

import java.io.IOException;
import com.kasperkiewicz.masters.common.Content;
import java.util.function.Supplier;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientAdapter {

    private static final String ADD_CONTENT_ENDPOINT = "/receiverImper";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String HTTP_PREFIX = "http://";
    private String host;
    private int port;

    private Gson gson = new Gson();
    private HttpClient httpClient = HttpClientBuilder.create().build();

    public HttpClientAdapter(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void addContent(Supplier<Content> content) {
        try {
            HttpPost postRequest = new HttpPost(constructAddContentPath());
            postRequest.addHeader(HttpHeaders.CONTENT_TYPE, JSON_CONTENT_TYPE);
            postRequest.setEntity(new StringEntity(gson.toJson(content.get())));
            httpClient.execute(postRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String constructAddContentPath() {
        return HTTP_PREFIX + host + ":" + port + ADD_CONTENT_ENDPOINT;
    }
}
