package com.example.zetta.operations.currencylayer.api;


import com.example.zetta.operations.convert.models.CurrencyConvertRequest;
import com.example.zetta.operations.exchange.models.ExchangeRateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyLayerApiService
{

    String API_KEY = "6072c279eec173215545554ca0faeac1";

    String BASE_URL = "http://api.currencylayer.com/";

    private final WebClient.Builder builder = WebClient.builder();

    private final ObjectMapper objectMapper;

    public CurrencyLayerApiService(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

    public double convertCurrency(CurrencyConvertRequest currencyConvertRequest)
    {
        //100 BGN USD
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

            return jsonNode.get("result").asDouble();
        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }

    }

    public double getExchangeRate(ExchangeRateRequest exchangeRateRequest)
    {
        // USD BGN
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

            return quotesNode.get(key).asDouble();
        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
}