package com.example.zetta.operations.convert;

import com.example.zetta.logging.LogBuilder;
import com.example.zetta.operations.convert.models.CurrencyConvertDetails;
import com.example.zetta.operations.convert.models.CurrencyConvertRequest;
import com.example.zetta.operations.convert.models.CurrencyConvertResult;
import com.example.zetta.operations.convert.models.SortByCreationDate;
import com.example.zetta.operations.convert.repository.ConvertHistoryDAO;
import com.example.zetta.operations.currencylayer.api.CurrencyLayerApiService;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class ConvertService
{
    private CurrencyLayerApiService currencyLayerApiService;

    private ConvertHistoryDAO convertHistoryDAO;

    public ConvertService(CurrencyLayerApiService currencyLayerApiService, ConvertHistoryDAO convertHistoryDAO)
    {
        this.currencyLayerApiService = currencyLayerApiService;
        this.convertHistoryDAO = convertHistoryDAO;
    }

    public CurrencyConvertResult getCurrencyConversion(CurrencyConvertRequest currencyConvertRequest)
    {
        if(currencyConvertRequest.amount().doubleValue() <= 0)
        {
            throw new IllegalArgumentException("Amount cant be less than 0");
        }

        BigDecimal convertedCurrencyAmount = currencyLayerApiService.convertCurrency(currencyConvertRequest);
        String randomId = UUID.randomUUID().toString();

        convertHistoryDAO.saveConversionInformation(currencyConvertRequest, randomId);

        log.info(LogBuilder.create("Currency conversion")
                .append("converted amount", convertedCurrencyAmount)
                .append("randomId", randomId)
                .additionalDetails(currencyConvertRequest)
                .build());


        return new CurrencyConvertResult(convertedCurrencyAmount, randomId);
    }

    public List<CurrencyConvertDetails> getCurrencyConversionHistory(SortByCreationDate sortByCreationDate, CurrencyCode currencyCode, int pageNumber, int pageSize)
    {
        if (pageNumber <= 0  || pageSize <= 0)
        {
            throw new IllegalArgumentException("Page number or size must be bigger than 0");
        }
        int offset = (pageNumber - 1) * pageSize;
        return convertHistoryDAO.getCurrencyConversionByFilter(sortByCreationDate, currencyCode, pageSize, offset);
    }
}
