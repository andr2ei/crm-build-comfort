package ru.andronov.crm.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class IncomePerMonthDTO {
    private final int year;
    private final int month;
    private final double totalTradePrice;
    private final double totalCost;
    private final double totalIncome;
}
