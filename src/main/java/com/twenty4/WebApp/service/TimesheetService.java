package com.twenty4.WebApp.service;

import com.twenty4.WebApp.repository.TimesheetRepository;
import org.springframework.stereotype.Service;

@Service
public class TimesheetService {
    private final TimesheetRepository timesheetRepository;

    public TimesheetService(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }
}
