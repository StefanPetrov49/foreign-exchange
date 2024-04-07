package com.example.zetta.operations.convert;

import com.example.zetta.operations.convert.models.CurrencyConvertDetails;
import com.example.zetta.operations.convert.models.CurrencyConvertRequest;
import com.example.zetta.operations.convert.models.CurrencyConvertResult;
import com.example.zetta.operations.convert.models.SortByCreationDate;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convert")
public class ConvertController
{
    private ConvertService convertService;

    public ConvertController(ConvertService convertService)
    {
        this.convertService = convertService;
    }

    @PostMapping()
    public CurrencyConvertResult getCurrencyConversion(@RequestBody CurrencyConvertRequest currencyConvertRequest)
    {
        return convertService.getCurrencyConversion(currencyConvertRequest);
    }

    @GetMapping("/history")
    public List<CurrencyConvertDetails> getCurrencyConversionHistory(@RequestParam(required = false) SortByCreationDate sortByCreationDate,
                                                                     @RequestParam(required = false) CurrencyCode currencyCode,
                                                                     @RequestParam int pageNumber,
                                                                     @RequestParam int pageSize)
    {
        return convertService.getCurrencyConversionHistory(sortByCreationDate, currencyCode, pageNumber, pageSize);
    }
}
