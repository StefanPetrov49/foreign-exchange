package com.example.zetta.operations.converison;

import com.example.zetta.operations.converison.models.CurrencyConversionRequest;
import com.example.zetta.operations.converison.models.CurrencyConversionResponse;
import com.example.zetta.operations.converison.models.SortByCreationDate;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("conversion")
public class ConversionController
{
    private ConversionService conversionService;

    public ConversionController(ConversionService conversionService)
    {
        this.conversionService = conversionService;
    }

    @GetMapping
    public CurrencyConversionResponse getCurrencyConversion(@RequestBody CurrencyConversionRequest currencyConversionRequest)
    {
        return conversionService.getCurrencyConversion(currencyConversionRequest);
    }

    @GetMapping("/history")
    public List<CurrencyConversionResponse> getCurrencyConversionHistory(@RequestParam(required = false) SortByCreationDate sortByCreationDate,
                                                                         @RequestParam(required = false) CurrencyCode currencyCode)
    {
        return conversionService.getCurrencyConversionHistory(sortByCreationDate, currencyCode);
    }
}
