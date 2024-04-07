package com.example.zetta.operations.convert.models;

import com.example.zetta.operations.exchange.models.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CurrencyConvertDetails(long transactionId, CurrencyCode fromCurrency,
                                     CurrencyCode toCurrency, BigDecimal amountConverted, LocalDateTime createdAt)
{}
