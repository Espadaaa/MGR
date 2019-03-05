package com.kasperkiewicz.masters.gui.simulator;

public final class DockerConstants {

    public static final String CLIENT_IMAGE_NAME = "masters.client";
    public static final String SERVER_HOST_NAME = "server";
    public static final int SERVER_IMPERATIVE_PORT = 8080;
    public static final int SERVER_REACTIVE_PORT = 8081;
    public static final String SERVER_IMPERATIVE_ENDPOINT = "/receiverImper";
    public static final String SERVER_REACTIVE_ENDPOINT = "/receiverReactive";

    private DockerConstants() {
    }
}
