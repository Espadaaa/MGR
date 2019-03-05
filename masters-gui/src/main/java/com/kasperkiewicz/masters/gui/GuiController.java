package com.kasperkiewicz.masters.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuiController {


    private static final String CLIENT_IMPERATIVE_IMAGE_NAME = "masters.imperative.client";
    private static final int SERVER_IMPERATIVE_PORT = 8081;
    private static final String CLIENT_REACTIVE_IMAGE_NAME = "masters.reactive.client";
    private static final int SERVER_REACTIVE_PORT = 8081;


    private Simulator simulator;

    @Autowired
    public GuiController(Simulator simulator) {
        this.simulator = simulator;
    }

    @GetMapping("/startSimulation")
    public String welcome() {
        return "add";
    }

    @PostMapping("/startSimulationImperative")
    public String run(@RequestParam(value = "clientCount") int clientCount,
        @RequestParam(value = "messageCount") int messageCount, @RequestParam(value = "startAfter") long startAfter) {

        System.out.println("Got post request on /startSimulation: " + clientCount + " " + messageCount);
        long startDate = System.currentTimeMillis() + (startAfter*1000);
        simulator.start(SERVER_IMPERATIVE_PORT, CLIENT_IMPERATIVE_IMAGE_NAME, clientCount, messageCount, startDate);
        return "add2";
    }

    @PostMapping("/startSimulationReactive")
    public String startSimulationReactive(@RequestParam(value = "clientCount") int clientCount,
        @RequestParam(value = "messageCount") int messageCount,@RequestParam(value = "startAfter") long startAfter) {

        System.out.println("Got post request on /startSimulation: " + clientCount + " " + messageCount);
        long startDate = System.currentTimeMillis() + (startAfter*1000);
        simulator.start(SERVER_REACTIVE_PORT, CLIENT_REACTIVE_IMAGE_NAME, clientCount, messageCount, startDate);
        return "add2";
    }
}
