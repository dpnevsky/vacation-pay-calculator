package com.dpnevsky.vacationpaycalculator.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class VacationPayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // 60_000 * 10 / 29.3 = 20_477.8156
    @Test
    public void testCalculateVacationPayWithDayCount() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("averageSalary", "60000")
                        .param("dayCount", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageSalary").value(60_000))
                .andExpect(jsonPath("$.dayCount").value(10))
                .andExpect(jsonPath("$.vacationPay").value(20477.82));
    }

    //  c 1 по 20 января, с учетом выходных и праздников, будет 10 рабочих дней
    @Test
    public void testCalculateVacationPayWithDates() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("averageSalary", "60000")
                        .param("dayCount", "20")
                        .param("startDate", LocalDate.of(2024, 1, 1).toString())
                        .param("endDate", LocalDate.of(2024, 1, 20).toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageSalary").value(60_000))
                .andExpect(jsonPath("$.dayCount").value(10))
                .andExpect(jsonPath("$.startDate").value("2024-01-01"))
                .andExpect(jsonPath("$.endDate").value("2024-01-20"))
                .andExpect(jsonPath("$.vacationPay").value(20477.82));
    }
}
