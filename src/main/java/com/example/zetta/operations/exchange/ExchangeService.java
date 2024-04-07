package com.example.zetta.operations.exchange;

import com.example.zetta.logging.LogBuilder;
import com.example.zetta.operations.currencylayer.api.CurrencyLayerApiService;
import com.example.zetta.operations.exchange.models.ExchangeRateRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Log4j2
public class ExchangeService
{
    private CurrencyLayerApiService currencyLayerApiService;

    public ExchangeService(CurrencyLayerApiService currencyLayerApiService)
    {
        this.currencyLayerApiService = currencyLayerApiService;
    }

    public BigDecimal getExchangeRate(ExchangeRateRequest exchangeRateRequest)
    {
        BigDecimal result = currencyLayerApiService.getExchangeRate(exchangeRateRequest);

        log.info(LogBuilder.create("Exchange rate")
                .append("rate", result)
                .additionalDetails(exchangeRateRequest)
                .build());
        return result;
    }
}
