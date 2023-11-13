package com.springmicroservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining routes in the API Gateway.
 *
 * This class provides a set of routes using Spring Cloud Gateway, allowing the
 * API Gateway to forward requests to various microservices based on the specified
 * paths. Each route is defined with specific filters and URIs.
 */
@Configuration
public class ApiGatewayConfiguration {

    /**
     * Configures the routes for the API Gateway using Spring Cloud Gateway.
     *
     * @param builder the RouteLocatorBuilder used to create routes
     * @return a RouteLocator defining the routes for the API Gateway
     */
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f
                                .addRequestHeader("Header", "Header")
                                .addRequestParameter("Param", "Param"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion"))
                .build();
    }

}
