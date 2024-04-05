package com.example.zetta.operations.convert;

import com.example.zetta.operations.convert.models.CurrencyConvertDetails;
import com.example.zetta.operations.convert.models.CurrencyConvertRequest;
import com.example.zetta.operations.convert.models.CurrencyConvertResponse;
import com.example.zetta.operations.convert.models.SortByCreationDate;
import com.example.zetta.operations.convert.repository.ConvertDAO;
import com.example.zetta.operations.currencylayer.api.CurrencyLayerApiService;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ConvertService
{
    private CurrencyLayerApiService currencyLayerApiService;

    private ConvertDAO convertDAO;

    public ConvertService(CurrencyLayerApiService currencyLayerApiService, ConvertDAO convertDAO)
    {
        this.currencyLayerApiService = currencyLayerApiService;
        this.convertDAO = convertDAO;
    }

    public CurrencyConvertResponse getCurrencyConversion(CurrencyConvertRequest currencyConvertRequest)
    {
        Random random = new Random();

        double convertedCurrencyAmount = currencyLayerApiService.convertCurrency(currencyConvertRequest);
        long randomId = Math.abs(random.nextLong());

        convertDAO.saveConversionInformation(currencyConvertRequest, randomId);

        return new CurrencyConvertResponse(convertedCurrencyAmount, randomId);
    }

    public List<CurrencyConvertDetails> getCurrencyConversionHistory(SortByCreationDate sortByCreationDate, CurrencyCode currencyCode, int pageNumber, int pageSize)
    {
        if (pageNumber <= 0  || pageSize <= 0)
        {
            throw new IllegalArgumentException("Page number or size must be bigger than 0");
        }
        int offset = (pageNumber - 1) * pageSize;
        return convertDAO.getCurrencyConversionByFilter(sortByCreationDate, currencyCode, pageSize, offset);
    }
}
