package com.dpnevsky.vacationpaycalculator.controllers;

import com.dpnevsky.vacationpaycalculator.dto.VacationPayResponse;
import com.dpnevsky.vacationpaycalculator.services.VacationPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @Autowired
    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping
    public ResponseEntity<VacationPayResponse> calculateVacationPay(
            @RequestParam BigDecimal averageSalary,
            @RequestParam int dayCount,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        BigDecimal vacationPay;
        if (startDate != null && endDate != null) {
            dayCount = vacationPayService.dayCountWithoutWeekendsAndHolidays(startDate, endDate);
            vacationPay = vacationPayService.calculateVacationPay(averageSalary, dayCount);
            VacationPayResponse response = VacationPayResponse.builder()
                    .averageSalary(averageSalary)
                    .dayCount(dayCount)
                    .startDate(startDate)
                    .endDate(endDate)
                    .vacationPay(vacationPay)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            vacationPay = vacationPayService.calculateVacationPay(averageSalary, dayCount);
            VacationPayResponse response = VacationPayResponse.builder()
                    .averageSalary(averageSalary)
                    .dayCount(dayCount)
                    .vacationPay(vacationPay)
                    .build();
            return ResponseEntity.ok(response);
        }
    }
}
