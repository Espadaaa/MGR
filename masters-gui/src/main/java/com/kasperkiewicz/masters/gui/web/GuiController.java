package com.kasperkiewicz.masters.gui.web;

import static com.kasperkiewicz.masters.gui.simulator.DockerConstants.SERVER_IMPERATIVE_ENDPOINT;
import static com.kasperkiewicz.masters.gui.simulator.DockerConstants.SERVER_IMPERATIVE_PORT;
import static com.kasperkiewicz.masters.gui.simulator.DockerConstants.SERVER_REACTIVE_ENDPOINT;
import static com.kasperkiewicz.masters.gui.simulator.DockerConstants.SERVER_REACTIVE_PORT;

import com.kasperkiewicz.masters.gui.simulator.SimulationParams;
import com.kasperkiewicz.masters.gui.simulator.Simulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuiController {

    private Simulator simulator;

    @Autowired
    public GuiController(Simulator simulator) {
        this.simulator = simulator;
    }

    @GetMapping("/simulationOptions")
    public String welcome() {
        return "Home";
    }

    @PostMapping("/startSimulationImperative")
    public String run(@RequestParam(value = "clientCount") int clientCount,
        @RequestParam(value = "messageCount") int messageCount,
        @RequestParam(value = "startAfter") long startAfter) {

        long startDate = System.currentTimeMillis() + (startAfter * 1000);
        SimulationParams params =
            new SimulationParams(SERVER_IMPERATIVE_PORT, SERVER_IMPERATIVE_ENDPOINT, clientCount, messageCount,
                startDate);
        return startSimulation(params);
    }

    @PostMapping("/startSimulationReactive")
    public String startSimulationReactive(@RequestParam(value = "clientCount") int clientCount,
        @RequestParam(value = "messageCount") int messageCount,
        @RequestParam(value = "startAfter") long startAfter) {

        long startDate = System.currentTimeMillis() + (startAfter * 1000);
        SimulationParams params =
            new SimulationParams(SERVER_REACTIVE_PORT, SERVER_REACTIVE_ENDPOINT, clientCount, messageCount, startDate);
        return startSimulation(params);
    }

    private String startSimulation(SimulationParams params) {
        System.out.println("Starting simulation: "
            + params.getClientCount() + " clients, " + params.getMessageCount() + " messages");
        simulator.start(params);
        return "add2";
    }
}
