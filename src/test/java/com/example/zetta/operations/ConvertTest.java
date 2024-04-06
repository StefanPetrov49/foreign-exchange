package com.example.zetta.operations;

import com.example.zetta.operations.convert.ConvertService;
import com.example.zetta.operations.convert.models.CurrencyConvertRequest;
import com.example.zetta.operations.convert.models.CurrencyConvertResponse;
import com.example.zetta.operations.currencylayer.api.CurrencyLayerApiService;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ConvertTest
{
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private JsonNode jsonNode;
    @InjectMocks
    private CurrencyLayerApiService currencyLayerApiService;

    @Test
    public void testGetCurrencyConversion()
    {
        //Given
        double amount = 100;
        CurrencyCode fromCurrency = CurrencyCode.BGN;
        CurrencyCode toCurrency = CurrencyCode.USD;
        CurrencyConvertRequest currencyConvertRequest = new CurrencyConvertRequest(amount, fromCurrency, toCurrency);
        //When
        double response = currencyLayerApiService.convertCurrency(currencyConvertRequest);
        //Then
        assertEquals(55.42, response);
    }
}
