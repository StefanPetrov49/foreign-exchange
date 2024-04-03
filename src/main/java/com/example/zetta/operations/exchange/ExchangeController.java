package com.example.zetta.operations.exchange;


import com.example.zetta.operations.exchange.models.ExchangeRateRequest;
import com.example.zetta.operations.exchange.models.ExchangeRateResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class ExchangeController
{
    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService)
    {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public ExchangeRateResponse getExchangeRate(@RequestBody ExchangeRateRequest exchangeRateRequest)
    {
        return exchangeService.getExchangeRate(exchangeRateRequest);
    }
}
