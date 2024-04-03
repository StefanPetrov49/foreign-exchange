package com.example.zetta.operations;

import com.example.zetta.operations.currencylayer.api.CurrencyLayerApiService;
import com.example.zetta.operations.models.*;
import com.example.zetta.operations.repository.OperationsDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationsService
{
    private CurrencyLayerApiService currencyLayerApiService;
    private OperationsDAO operationsDAO;

    public OperationsService(CurrencyLayerApiService currencyLayerApiService, OperationsDAO operationsDAO)
    {
        this.currencyLayerApiService = currencyLayerApiService;
        this.operationsDAO = operationsDAO;
    }

    public ExchangeRateResponse getExchangeRate(ExchangeRateRequest exchangeRateRequest)
    {
        return null;
    }

    public CurrencyConversionResponse getCurrencyConversion(CurrencyConversionRequest currencyConversionRequest)
    {
        return null;
    }

    public List<CurrencyConversionResponse> getCurrencyConversionHistory(SortByCreationDate sortByCreationDate, CurrencyCode currencyCode)
    {
        return null;
    }
}
