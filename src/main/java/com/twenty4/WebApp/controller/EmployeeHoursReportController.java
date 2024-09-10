package com.twenty4.WebApp.controller;

import com.twenty4.WebApp.model.EmployeeHours;
import com.twenty4.WebApp.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee-hours-report")
public class EmployeeHoursReportController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @GetMapping
    public List<EmployeeHours> getEmployeeHoursReport(
            @RequestParam("weekEnding") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate weekEnding
    ) {
        return timesheetRepository.findEmployeeHoursByWeekEnding(weekEnding);
    }
}
