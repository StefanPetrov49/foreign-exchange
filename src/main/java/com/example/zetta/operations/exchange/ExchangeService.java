package com.example.zetta.operations.exchange;

import com.example.zetta.operations.currencylayer.api.CurrencyLayerApiService;
import com.example.zetta.operations.exchange.models.ExchangeRateRequest;
import com.example.zetta.operations.exchange.models.ExchangeRateResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService
{
    private CurrencyLayerApiService currencyLayerApiService;

    public ExchangeService(CurrencyLayerApiService currencyLayerApiService)
    {
        this.currencyLayerApiService = currencyLayerApiService;
    }

    public double getExchangeRate(ExchangeRateRequest exchangeRateRequest)
    {
        return currencyLayerApiService.getExchangeRate(exchangeRateRequest);
    }
}
