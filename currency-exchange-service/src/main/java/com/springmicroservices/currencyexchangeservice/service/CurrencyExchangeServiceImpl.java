package com.springmicroservices.currencyexchangeservice.service;

import com.springmicroservices.currencyexchangeservice.entity.CurrencyExchange;
import com.springmicroservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final CurrencyExchangeRepository currencyExchangeRepository;

    @Override
    public CurrencyExchange findCurrencyExchangeByFromAndTo(String from, String to) {
        return currencyExchangeRepository.findByFromAndTo(from, to);
    }
}
