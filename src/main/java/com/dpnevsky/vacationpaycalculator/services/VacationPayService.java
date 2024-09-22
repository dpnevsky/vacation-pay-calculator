package com.dpnevsky.vacationpaycalculator.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@NoArgsConstructor
public class VacationPayService {

    private static final Logger logger = LoggerFactory.getLogger(VacationPayService.class);
    // коэффициент для расчета среднего заработка
    @Getter
    private static final BigDecimal AVERAGE_MONTH_DAYS = BigDecimal.valueOf(29.3);
    // праздничные дни
    @Getter
    private static final Set<LocalDate> HOLIDAYS = new HashSet<>();
    static {
            HOLIDAYS.add(LocalDate.of(2024, 1, 1));
            HOLIDAYS.add(LocalDate.of(2024, 1, 2));
            HOLIDAYS.add(LocalDate.of(2024, 1, 3));
            HOLIDAYS.add(LocalDate.of(2024, 1, 4));
            HOLIDAYS.add(LocalDate.of(2024, 1, 5));
            HOLIDAYS.add(LocalDate.of(2024, 1, 6));
            HOLIDAYS.add(LocalDate.of(2024, 1, 7));
            HOLIDAYS.add(LocalDate.of(2024, 2, 23));
            HOLIDAYS.add(LocalDate.of(2024, 3, 8));
            HOLIDAYS.add(LocalDate.of(2024, 5, 1));
            HOLIDAYS.add(LocalDate.of(2024, 5, 9));
            HOLIDAYS.add(LocalDate.of(2024, 6, 12));
            HOLIDAYS.add(LocalDate.of(2024, 11, 4));
            HOLIDAYS.add(LocalDate.of(2025, 1, 1));
            HOLIDAYS.add(LocalDate.of(2025, 1, 2));
            HOLIDAYS.add(LocalDate.of(2025, 1, 3));
            HOLIDAYS.add(LocalDate.of(2025, 1, 4));
            HOLIDAYS.add(LocalDate.of(2025, 1, 5));
            HOLIDAYS.add(LocalDate.of(2025, 1, 6));
            HOLIDAYS.add(LocalDate.of(2025, 1, 7));
            HOLIDAYS.add(LocalDate.of(2025, 2, 23));
            HOLIDAYS.add(LocalDate.of(2025, 3, 8));
            HOLIDAYS.add(LocalDate.of(2025, 5, 1));
            HOLIDAYS.add(LocalDate.of(2025, 5, 9));
            HOLIDAYS.add(LocalDate.of(2025, 6, 12));
            HOLIDAYS.add(LocalDate.of(2025, 11, 4));
    }

    public BigDecimal calculateVacationPay(BigDecimal averageSalary, int dayCount) {

        if (dayCount < 1){
            throw new IllegalArgumentException("Day count entered incorrectly.");
        }

        if (averageSalary.compareTo(BigDecimal.ONE) < 1){
            throw new IllegalArgumentException("Average salary entered incorrectly.");
        }

        BigDecimal vacationPay = averageSalary.
                multiply(BigDecimal.valueOf(dayCount)).divide(AVERAGE_MONTH_DAYS, 2, RoundingMode.HALF_EVEN);

        logger.info("Calculated vacation pay: {}", vacationPay);
        return vacationPay;
    }

    public int dayCountWithoutWeekendsAndHolidays(LocalDate startDate, LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Dates entered incorrectly.");
        }

        int dayCount = 0;

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || HOLIDAYS.contains(date)) {
                continue;
            }
            dayCount++;
        }

        return  dayCount;
    }
}







