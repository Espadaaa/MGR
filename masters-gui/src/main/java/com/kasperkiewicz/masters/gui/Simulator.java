package com.kasperkiewicz.masters.gui;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class Simulator {

    private static final String SERVER_HOST_NAME = "server";

    public void start(int serverPort, String clientImageName, int clientCount, int messageCount, long startDate) {
        System.out.println("Started simulation");

        try {
            for (int i = 0; i < clientCount; i++) {
                DockerClient docker = DefaultDockerClient.fromEnv().build();
                HostConfig hostConfig = HostConfig
                    .builder()
                    .links(SERVER_HOST_NAME + ":" + SERVER_HOST_NAME)
                    .build();

                ContainerConfig containerConfig = ContainerConfig.builder()
                    .hostConfig(hostConfig)
                    .image(clientImageName)
                    .cmd(SERVER_HOST_NAME, Integer.toString(serverPort), Integer.toString(messageCount),
                        UUID.randomUUID().toString(), Long.toString(startDate))
                    .build();

                ContainerCreation creation = docker.createContainer(containerConfig);
                String id = creation.id();
                docker.startContainer(id);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
