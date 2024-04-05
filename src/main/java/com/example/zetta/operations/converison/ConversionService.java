package com.example.zetta.operations.converison;

import com.example.zetta.operations.converison.models.CurrencyConversionRequest;
import com.example.zetta.operations.converison.models.CurrencyConversionResponse;
import com.example.zetta.operations.converison.models.SortByCreationDate;
import com.example.zetta.operations.currencylayer.api.CurrencyLayerApiService;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversionService
{
    private CurrencyLayerApiService currencyLayerApiService;

    public ConversionService(CurrencyLayerApiService currencyLayerApiService)
    {
        this.currencyLayerApiService = currencyLayerApiService;
    }

    public void getCurrencyConversion()
    {
         currencyLayerApiService.convertCurrency();
    }

    public List<CurrencyConversionResponse> getCurrencyConversionHistory(SortByCreationDate sortByCreationDate, CurrencyCode currencyCode)
    {
        return null;
    }
}
