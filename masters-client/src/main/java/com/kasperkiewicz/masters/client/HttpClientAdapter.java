package com.kasperkiewicz.masters.client;

import static com.kasperkiewicz.masters.client.ClientConstants.CLIENT_CONTENT_TYPE;
import static com.kasperkiewicz.masters.client.ClientConstants.HTTP_PREFIX;

import com.google.gson.Gson;
import com.kasperkiewicz.masters.common.Content;
import java.io.IOException;
import java.util.function.Supplier;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientAdapter {

    private String host;
    private int port;
    private String endpoint;

    private Gson gson = new Gson();
    private HttpClient httpClient = HttpClientBuilder.create().build();

    public HttpClientAdapter(String host, int port, String endpoint) {
        this.host = host;
        this.port = port;
        this.endpoint = endpoint;
    }

    public void addContent(Supplier<Content> content) throws IOException {

            HttpPost postRequest = new HttpPost(constructAddContentPath());
            postRequest.addHeader(HttpHeaders.CONTENT_TYPE, CLIENT_CONTENT_TYPE);
            postRequest.setEntity(new StringEntity(gson.toJson(content.get())));
            httpClient.execute(postRequest);


    }

    private String constructAddContentPath() {
        return HTTP_PREFIX + host + ":" + port + endpoint;
    }
}
