package com.example.zetta.operations.exchange;


import com.example.zetta.operations.exchange.models.ExchangeRateRequest;
import com.example.zetta.operations.exchange.models.ExchangeRateResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange")
public class ExchangeController
{
    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService)
    {
        this.exchangeService = exchangeService;
    }

    @PostMapping
    public BigDecimal getExchangeRate(@RequestBody ExchangeRateRequest exchangeRateRequest)
    {
        return exchangeService.getExchangeRate(exchangeRateRequest);
    }
}
