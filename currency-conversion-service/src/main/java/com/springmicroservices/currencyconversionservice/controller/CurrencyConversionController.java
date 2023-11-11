package com.springmicroservices.currencyconversionservice.controller;

import com.springmicroservices.currencyconversionservice.model.CurrencyConversion;
import com.springmicroservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    //USING FEIGN CLIENT
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                               @PathVariable String to,
                                                               @PathVariable BigDecimal quantity){

        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return CurrencyConversion.builder()
                .id(10001L)
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(currencyConversion.getConversionMultiple())
                .totalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()))
                .environment(currencyConversion.getEnvironment() + " feign")
                .build();
    }

    //USING REST TEMPLATE
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionRestTemplate(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity){

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        CurrencyConversion currencyConversion = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class,
                        uriVariables).getBody();

        return CurrencyConversion.builder()
                .id(10001L)
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(currencyConversion.getConversionMultiple())
                .totalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()))
                .environment(currencyConversion.getEnvironment() + " rest template")
                .build();
    }

}
