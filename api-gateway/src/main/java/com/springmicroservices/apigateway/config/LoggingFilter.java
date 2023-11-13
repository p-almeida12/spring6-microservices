package com.springmicroservices.apigateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * Global filter for logging incoming requests in the API Gateway.
 *
 * This filter implements the Spring Cloud Gateway GlobalFilter interface
 * to log information about incoming requests, such as the path.
 */
@Component
public class LoggingFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    /**
     * Filters incoming requests to log information about the request path.
     *
     * @param exchange the ServerWebExchange representing the incoming request and response
     * @param chain the GatewayFilterChain for executing the next filters in the chain
     * @return a Mono<Void> indicating completion of the filtering process
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        logger.info("Path of the request received -> {}",
                exchange.getRequest().getPath());
        return chain.filter(exchange);
    }

}
