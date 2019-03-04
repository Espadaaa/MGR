package com.kasperkiewicz.masters.reactive.client;


import com.google.gson.Gson;
import com.kasperkiewicz.masters.common.Content;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;

public class HttpClientAdapter {

    private static final String ADD_CONTENT_ENDPOINT = "/receiverReact";
    private Gson gson = new Gson();
    private HttpClient client;

    public HttpClientAdapter(String host, int port) {
        client = HttpClient.create().baseUrl(host + ":" + port);
    }

    public Mono<HttpClientResponse> addContent(Content content) {
        return client
            .post()
            .uri(ADD_CONTENT_ENDPOINT)
            .send(encodeContent(content))
            .response();
    }

    private Mono<ByteBuf> encodeContent(Content content) {
        return Mono.just(Unpooled.wrappedBuffer(gson.toJson(content).getBytes()));
    }
}
