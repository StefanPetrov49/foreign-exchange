package com.example.zetta.operations.currencylayer.api;


import com.example.zetta.operations.convert.models.CurrencyConvertRequest;
import com.example.zetta.operations.exchange.models.ExchangeRateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class CurrencyLayerApiService
{
    @Value("${currencylayer.api.key}")
    private String API_KEY;

    @Value("${currencylayer.api.url}")
    private String BASE_URL;

    private final WebClient.Builder builder = WebClient.builder();

    private final ObjectMapper objectMapper;

    public CurrencyLayerApiService(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

    public BigDecimal convertCurrency(CurrencyConvertRequest currencyConvertRequest)
    {
        String uri = BASE_URL + "convert?access_key=" + API_KEY + "&from="
                + currencyConvertRequest.fromCurrencyCode() + "&to="
                + currencyConvertRequest.toCurrencyCode() + "&amount="
                + currencyConvertRequest.amount();

        String json = builder.build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try
        {
            JsonNode jsonNode = objectMapper.readTree(json);

            return BigDecimal.valueOf(jsonNode.get("result").asDouble());
        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }

    public BigDecimal getExchangeRate(ExchangeRateRequest exchangeRateRequest)
    {
        String uri = BASE_URL + "live?access_key=" + API_KEY + "&currencies="
                + exchangeRateRequest.desiredCurrencyCode() + "&source="
                + exchangeRateRequest.sourceCurrencyCode();

        String json = builder.build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try
        {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode quotesNode = jsonNode.get("quotes");
            String key = exchangeRateRequest.sourceCurrencyCode().name() + exchangeRateRequest.desiredCurrencyCode().name();
            return BigDecimal.valueOf(quotesNode.get(key).asDouble());
        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
}