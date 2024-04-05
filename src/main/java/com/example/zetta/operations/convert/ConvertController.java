package com.example.zetta.operations.convert;

import com.example.zetta.operations.convert.models.CurrencyConvertDetails;
import com.example.zetta.operations.convert.models.CurrencyConvertRequest;
import com.example.zetta.operations.convert.models.CurrencyConvertResponse;
import com.example.zetta.operations.convert.models.SortByCreationDate;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;

@RestController
@RequestMapping("/conversion")
public class ConvertController
{
    private ConvertService convertService;

    public ConvertController(ConvertService convertService)
    {
        this.convertService = convertService;
    }

    @PostMapping()
    public CurrencyConvertResponse getCurrencyConversion(@RequestBody CurrencyConvertRequest currencyConvertRequest)
    {
        System.out.println(currencyConvertRequest.toCurrencyCode().name());
        return convertService.getCurrencyConversion(currencyConvertRequest);
    }

    @GetMapping("/history")
    public List<CurrencyConvertDetails> getCurrencyConversionHistory(@RequestParam(required = false) SortByCreationDate sortByCreationDate,
                                                                     @RequestParam(required = false) CurrencyCode currencyCode)
    {
        return convertService.getCurrencyConversionHistory(sortByCreationDate, currencyCode);
    }
}
