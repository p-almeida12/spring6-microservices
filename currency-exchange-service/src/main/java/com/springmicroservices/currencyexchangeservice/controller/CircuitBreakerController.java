package com.springmicroservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api-retry")
    @Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
    public String sampleApiMethodRetry(){
        logger.info("Sample API call received.");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost8000/some-url", String.class);

        return responseEntity.getBody();
    }

    @GetMapping("/sample-api-circuit-breaker")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    public String sampleApiMethodCircuitBreaker(){
        logger.info("Sample API call received.");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost8000/some-url", String.class);

        return responseEntity.getBody();
    }

    @GetMapping("/sample-api-rate-limiter")
    @RateLimiter(name = "default") //limit the rate that the requests are made e.g. 10s -> max 1000 requests for this api
    public String sampleApiMethodRateLimiter(){
        return "sample-api-rate-limiter";
    }

    @GetMapping("/sample-api-bulkhead")
    @Bulkhead(name = "sample-api-bulkhead") //concurrent calls limiter
    public String sampleApiMethodBulkHead(){
        return "sample-api-rate-limiter";
    }

    public String fallbackMethod(Exception exception) {
        return "fallback-response";
    }

}
