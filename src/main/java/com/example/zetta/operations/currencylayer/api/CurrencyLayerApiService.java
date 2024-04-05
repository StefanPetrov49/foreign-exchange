package com.example.zetta.operations.currencylayer.api;

import com.example.zetta.operations.exchange.models.CurrencyCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyLayerApiService
{
    @Value("currency.layer.base.url")
    private String BASE_URL;

    @Value("${currencylayer.api.key}")
    private String API_KEY;

    public void convertCurrency()
    {
        WebClient.Builder builder = WebClient.builder();
        String baseUrl = "http://api.currencylayer.com/";
        String apiKey = "3bb2133ac35ae48c1b5905633ad7b9e0";
        CurrencyCode usd = CurrencyCode.USD;
        CurrencyCode eur = CurrencyCode.EUR;
        int amount = 100;

        String uri = baseUrl+"convert?from="+eur+"&to="+usd+"&amount="+amount+"?access_key="+apiKey;
        String uri2 = "http://apilayer.net/api/live?access_key=3bb2133ac35ae48c1b5905633ad7b9e0&currencies=EUR";

        String convert = builder.build()
                .get()
                .uri(uri2)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("----------------------------------------------");
        System.out.println(convert);
        System.out.println("----------------------------------------------");
    }
}

// "convert" - convert one currency to another
//    https://api.currencylayer.com/convert?from=EUR&to=GBP&amount=100}
