package com.example.zetta.service;

import com.example.zetta.operations.convert.models.CurrencyConvertDetails;
import com.example.zetta.operations.convert.models.SortByCreationDate;
import com.example.zetta.operations.convert.repository.ConvertDAO;
import com.example.zetta.operations.exchange.models.CurrencyCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@JdbcTest
@Sql({"/test_schema.sql"})
@ExtendWith(MockitoExtension.class)
public class ConvertServiceTest
{
   @Mock
   private JdbcTemplate jdbcTemplate;
   @InjectMocks
   private ConvertDAO convertDAO;

    @Test
    void whenInjectInMemoryDataSource_thenReturnCorrectBGNCount() {
        SortByCreationDate sort = SortByCreationDate.ASC;
        CurrencyCode currency = CurrencyCode.BGN;
        int pageSize = 1;
        int offset = 1;
        List<CurrencyConvertDetails> result = convertDAO.getCurrencyConversionByFilter(sort, currency, pageSize, offset);

        Assertions.assertEquals(3, result.size());
    }
}
