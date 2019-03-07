package com.kasperkiewicz.masters.server.reactive.http;

import com.kasperkiewicz.masters.server.reactive.rest.ContentHandler;
import com.kasperkiewicz.masters.server.reactive.rest.MetricsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class HttpRouter {

    @Bean
    public RouterFunction<ServerResponse> contentTest(ContentHandler contentHandler) {
        return nest(path("/receiverReact"),
            route(GET("/"), contentHandler::getWholeContent)
                .andRoute(POST("/"), contentHandler::addContent));

    }

    @Bean
    public RouterFunction<ServerResponse> routeMetrics(MetricsHandler metricsHandler) {
        return nest(path("/metrics"),
            route(GET("/global/total"), metricsHandler::globalTotalHandleTime)
                .andRoute(GET("/global/average"), metricsHandler::globalAverageHandleTime)
                .andRoute(GET("/global/request"), metricsHandler::globalRequestCount)
                .andRoute(GET("/clients/total"), metricsHandler::getTotalHandleTimeForClients)
                .andRoute(GET("/clients/average"), metricsHandler::averageHandleTimeForClients));
    }
}
