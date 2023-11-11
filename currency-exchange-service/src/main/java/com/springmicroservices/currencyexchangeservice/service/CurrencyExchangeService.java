package com.springmicroservices.currencyexchangeservice.service;

import com.springmicroservices.currencyexchangeservice.entity.CurrencyExchange;

public interface CurrencyExchangeService {

    CurrencyExchange findCurrencyExchangeByFromAndTo(String from, String to);

}
