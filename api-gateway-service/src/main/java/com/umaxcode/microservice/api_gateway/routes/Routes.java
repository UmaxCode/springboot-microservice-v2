package com.umaxcode.microservice.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productsRoute() {
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.path("/api/v1/products")
                        , HandlerFunctions.http("http://localhost:8000"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ordersRoute() {
        return GatewayRouterFunctions.route("order-service")
                .route(RequestPredicates.path("/api/v1/orders"),
                        HandlerFunctions.http("http://localhost:8001"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryRoute() {
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.path("/api/v1/inventories"),
                        HandlerFunctions.http("http://localhost:8002"))
                .build();
    }
}
