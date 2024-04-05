package com.example.zetta.operations.currencylayer.api;


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

    public void convertCurrency()
    {
//            objectMapper;
//        String baseUrl = "http://api.currencylayer.com/";
//        CurrencyCode usd = CurrencyCode.USD;
//        CurrencyCode eur = CurrencyCode.EUR;
//        int amount = 100;
//
//        String uri = baseUrl+"convert?from="+eur+"&to="+usd+"&amount="+amount+"?access_key="+apiKey;
//        String uri2 = "http://apilayer.net/api/live?access_key=3bb2133ac35ae48c1b5905633ad7b9e0&currencies=EUR";
//
//        String convert = builder.build()
//                .get()
//                .uri(uri2)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//        System.out.println("----------------------------------------------");
//        System.out.println(convert);
//        System.out.println("----------------------------------------------");
    }

    public double getExchangeRate(ExchangeRateRequest exchangeRateRequest) throws JsonProcessingException
    {
        String uri = BASE_URL +"live?access_key=" + API_KEY + "&currencies="
                + exchangeRateRequest.desiredCurrencyCode()+"&source="
                + exchangeRateRequest.sourceCurrencyCode();

        String json = builder.build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        JsonNode quotesNode = jsonNode.get("quotes");

        String key = exchangeRateRequest.sourceCurrencyCode().name()+exchangeRateRequest.desiredCurrencyCode().name();

        return quotesNode.get(key).asDouble();
    }
}

// "convert" - convert one currency to another
//    https://api.currencylayer.com/convert?from=EUR&to=GBP&amount=100}
