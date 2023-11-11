package com.springmicroservices.currencyexchangeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Currency Exchange Not Found")
public class CurrencyExchangeNotFoundException extends RuntimeException{
    public CurrencyExchangeNotFoundException() {
    }

    public CurrencyExchangeNotFoundException(String message) {
        super(message);
    }

    public CurrencyExchangeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyExchangeNotFoundException(Throwable cause) {
        super(cause);
    }

    public CurrencyExchangeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
