package com.example.zetta.operations.convert.models;

import com.example.zetta.operations.exchange.models.CurrencyCode;

public record CurrencyConvertRequest(double amount, CurrencyCode fromCurrencyCode, CurrencyCode toCurrencyCode)
{}
