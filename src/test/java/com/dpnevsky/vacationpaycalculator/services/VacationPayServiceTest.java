package com.dpnevsky.vacationpaycalculator.services;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class VacationPayServiceTest {

    VacationPayService service = new VacationPayService();

    @Test
    void calculateVacationPay_withDayCount() {
        // 60_000 * 10 / 29.3 = 20_477.8156
        BigDecimal averageSalary = new BigDecimal("60000");
        int dayCount = 10;
        BigDecimal vacationPay = service.calculateVacationPay(averageSalary, dayCount);
        assertEquals(new BigDecimal("20477.82"), vacationPay);
    }

    @Test
    void calculateVacationPay_withDates() {
        //  c 1 по 20 января, с учетом выходных и праздников, будет 10 рабочих дней
        int dayCount = service.dayCountWithoutWeekendsAndHolidays(LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 20));
        BigDecimal vacationPay = service.calculateVacationPay(new BigDecimal("60000"), dayCount);
        assertEquals(new BigDecimal("20477.82"), vacationPay);
    }
}