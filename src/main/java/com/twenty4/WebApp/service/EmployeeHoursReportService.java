package com.twenty4.WebApp.service;

import com.twenty4.WebApp.model.EmployeeHours;
import com.twenty4.WebApp.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeHoursReportService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    public List<EmployeeHours> getEmployeeHoursReport(LocalDate weekEnding) {
        return timesheetRepository.findEmployeeHoursByWeekEnding(weekEnding);
    }
}