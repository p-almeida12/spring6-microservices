package com.springmicroservices.currencyexchangeservice.controller;

import com.springmicroservices.currencyexchangeservice.entity.CurrencyExchange;
import com.springmicroservices.currencyexchangeservice.exception.CurrencyExchangeNotFoundException;
import com.springmicroservices.currencyexchangeservice.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from,
                                             @PathVariable String to){

        CurrencyExchange currencyExchange = currencyExchangeService
                .findCurrencyExchangeByFromAndTo(from, to);

        if(currencyExchange == null){
            throw new CurrencyExchangeNotFoundException("Unable to convert from " + from + " to " + to);
        }

        //get the port that the current instance is running on
        String port = environment.getProperty("local.server.port");

        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }

}
