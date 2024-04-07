package com.example.zetta.service;

import com.example.zetta.operations.convert.ConvertService;
import com.example.zetta.operations.convert.models.CurrencyConvertRequest;
import com.example.zetta.operations.convert.models.CurrencyConvertResult;
import com.example.zetta.operations.convert.models.SortByCreationDate;
import com.example.zetta.operations.convert.repository.ConvertHistoryDAO;
import com.example.zetta.operations.currencylayer.api.CurrencyLayerApiService;
import com.example.zetta.operations.exchange.ExchangeService;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import com.example.zetta.operations.exchange.models.ExchangeRateRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyLayerApiServiceTest
{
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private JsonNode jsonNode;
    @Mock
    private CurrencyLayerApiService currencyLayerApiService;
    @InjectMocks
    private ExchangeService exchangeService;

    @Mock
    private ConvertHistoryDAO convertHistoryDAO;
    @InjectMocks
    private ConvertService convertService;

    @Test
    public void testGetCurrencyConversion()
    {
        //Given
        BigDecimal expectedAns = BigDecimal.valueOf(56.01);
        CurrencyConvertRequest req = new CurrencyConvertRequest(BigDecimal.valueOf(100), CurrencyCode.USD, CurrencyCode.BGN);
        Mockito.when(currencyLayerApiService.convertCurrency(req))
                .thenReturn(expectedAns);
        //When
        CurrencyConvertResult result = convertService.getCurrencyConversion(req);
        //Then
        assertEquals(expectedAns, result.convertedAmount());
        assertNotNull(result.transactionId());
    }

    @Test
    public void testGetExchangeRate()
    {
        // Given
        BigDecimal expectedAns = BigDecimal.valueOf(0.92);
        ExchangeRateRequest req = new ExchangeRateRequest(CurrencyCode.USD, CurrencyCode.BGN);
        Mockito.when(currencyLayerApiService.getExchangeRate(req))
                .thenReturn(expectedAns);
        //When
        BigDecimal result = exchangeService.getExchangeRate(req);
        //Then
        assertEquals(expectedAns, result);
    }

    @Test
    public void testGetCurrencyConversionHistoryThrowsError()
    {
        //Given
        SortByCreationDate sort = SortByCreationDate.ASC;
        CurrencyCode currency = CurrencyCode.BGN;
        int pageNumber= 0;
        int pageSize = 1;
        //When
        var exception = assertThrows(
                IllegalArgumentException.class, () -> convertService.getCurrencyConversionHistory(sort, currency, pageNumber, pageSize));
        //Then
        assertEquals("Page number or size must be bigger than 0", exception.getMessage());
    }
}
