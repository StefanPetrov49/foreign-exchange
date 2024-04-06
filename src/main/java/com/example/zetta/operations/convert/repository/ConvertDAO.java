package com.example.zetta.operations.convert.repository;

import com.example.zetta.operations.convert.models.CurrencyConvertDetails;
import com.example.zetta.operations.convert.models.CurrencyConvertRequest;
import com.example.zetta.operations.convert.models.SortByCreationDate;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConvertDAO
{
    private JdbcTemplate jdbcTemplate;

    public ConvertDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveConversionInformation(CurrencyConvertRequest currencyConvertRequest, String randomId)
    {
        String sql = """
                INSERT INTO conversion_history (transaction_id, from_currency, to_currency, amount_converted)
                VALUES (?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql, randomId, currencyConvertRequest.fromCurrencyCode().name(),
                currencyConvertRequest.toCurrencyCode().name(), currencyConvertRequest.amount());
    }

    public List<CurrencyConvertDetails> getCurrencyConversionByFilter(SortByCreationDate sortByCreationDate, CurrencyCode currencyCode, int pageSize, int offset)
    {
        String sql = """
                SELECT *
                FROM conversion_history ch
                WHERE ch.from_currency = COALESCE(?, ch.from_currency) OR ch.to_currency = COALESCE(?, ch.to_currency)
                ORDER BY ch.created_at""" + " " + (sortByCreationDate == null ? "ASC" : sortByCreationDate.name())
                + " LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, DataClassRowMapper.newInstance(CurrencyConvertDetails.class),
                (currencyCode == null ? null : currencyCode.name()),
                (currencyCode == null ? null : currencyCode.name()),
                pageSize, offset);
    }
}
