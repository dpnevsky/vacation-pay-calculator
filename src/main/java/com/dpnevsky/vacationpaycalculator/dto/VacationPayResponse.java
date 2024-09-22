package com.dpnevsky.vacationpaycalculator.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class VacationPayResponse {
    private BigDecimal averageSalary;
    private int dayCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal vacationPay;
}

