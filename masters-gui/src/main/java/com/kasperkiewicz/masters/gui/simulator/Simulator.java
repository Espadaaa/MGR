package com.kasperkiewicz.masters.gui.simulator;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.kasperkiewicz.masters.gui.simulator.DockerConstants.CLIENT_IMAGE_NAME;
import static com.kasperkiewicz.masters.gui.simulator.DockerConstants.SERVER_HOST_NAME;

@Component
public class Simulator {

    public void start(SimulationParams params) {
        try {
            for (int i = 0; i < params.getClientCount(); i++) {
                DockerClient docker = DefaultDockerClient.fromEnv().build();
                HostConfig hostConfig = HostConfig
                    .builder()
                    .links(SERVER_HOST_NAME + ":" + SERVER_HOST_NAME)
                    .build();

                ContainerConfig containerConfig = ContainerConfig.builder()
                    .hostConfig(hostConfig)
                    .image(CLIENT_IMAGE_NAME)
                    .cmd(SERVER_HOST_NAME, Integer.toString(params.getServerPort()),
                        params.getEndpoint(), Integer.toString(params.getMessageCount()),
                        UUID.randomUUID().toString(), Long.toString(params.getStartTime()))
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
