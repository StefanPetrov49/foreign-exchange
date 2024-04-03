package com.example.zetta.operations;

import com.example.zetta.operations.models.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("operations")
public class OperationsController
{
    private OperationsService operationsService;

    public OperationsController(OperationsService operationsService)
    {
        this.operationsService = operationsService;
    }

    @GetMapping("/exchange")
    public ExchangeRateResponse getExchangeRate(@RequestBody ExchangeRateRequest exchangeRateRequest)
    {
        return operationsService.getExchangeRate(exchangeRateRequest);
    }

    @GetMapping("/conversion")
    public CurrencyConversionResponse getCurrencyConversion(@RequestBody CurrencyConversionRequest currencyConversionRequest)
    {
        return operationsService.getCurrencyConversion(currencyConversionRequest);
    }

    @GetMapping("/history")
    public List<CurrencyConversionResponse> getCurrencyConversionHistory(@RequestParam(required = false) SortByCreationDate sortByCreationDate,
                                                                         @RequestParam(required = false) CurrencyCode currencyCode)
    {
        return operationsService.getCurrencyConversionHistory(sortByCreationDate, currencyCode);
    }
}
