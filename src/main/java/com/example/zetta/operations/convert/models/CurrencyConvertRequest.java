package com.example.zetta.operations.convert.models;

import com.example.zetta.operations.exchange.models.CurrencyCode;

import java.math.BigDecimal;

public record CurrencyConvertRequest(BigDecimal amount, CurrencyCode fromCurrencyCode, CurrencyCode toCurrencyCode)
{}
