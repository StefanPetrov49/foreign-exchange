package com.example.zetta.operations.convert.models;

import java.math.BigDecimal;

public record CurrencyConvertResult(BigDecimal convertedAmount, String transactionId)
{}
