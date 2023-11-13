package com.springmicroservices.currencyconversionservice.proxy;

import com.springmicroservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client interface for interacting with the "currency-exchange" microservice.
 *
 * This interface defines methods for making HTTP requests to the "currency-exchange" service,
 * specifically retrieving currency conversion information based on the provided currency codes.
 */
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    /**
     * Retrieves currency conversion information from the "currency-exchange" microservice.
     *
     * @param from the currency code to convert from
     * @param to the currency code to convert to
     * @return a CurrencyConversion object representing the exchange value
     */
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}
