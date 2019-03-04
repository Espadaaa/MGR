package com.kasperkiewicz.masters.reactive.server.rest;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HttpRouter {

    @Bean
    public RouterFunction<ServerResponse> contentTest(ContentHandler contentHandler) {
        return route(GET("/receiverReact"), contentHandler::getWholeContent)
            .andRoute(POST("/receiverReact"), contentHandler::addContent);

    }

//    @Bean
//    public RouterFunction<ServerResponse> routeMetrics(MetricsHandler metricsHandler) {
//        return route(GET("/metrics/average"), metricsHandler::getMetricsAverage)
//            .andRoute(GET("/metrics/total"), metricsHandler::getMetricsTotal);
//    }
}
