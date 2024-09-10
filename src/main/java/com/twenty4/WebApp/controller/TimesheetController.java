package com.twenty4.WebApp.controller;

import com.twenty4.WebApp.entity.Timesheet;
import com.twenty4.WebApp.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @GetMapping
    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getTimesheetById(@PathVariable int id) {
        Optional<Timesheet> timesheet = timesheetRepository.findById(id);
        return timesheet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<List<Timesheet>> createTimesheets(@RequestBody List<Timesheet> timesheets) {
        List<Timesheet> createdTimesheets = timesheetRepository.saveAll(timesheets);
        return new ResponseEntity<>(createdTimesheets, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(@PathVariable int id, @RequestBody Timesheet timesheet) {
        Optional<Timesheet> existingTimesheet = timesheetRepository.findById(id);
        if (existingTimesheet.isPresent()) {
            timesheet.setTimesheetId(id);
            Timesheet updatedTimesheet = timesheetRepository.save(timesheet);
            return new ResponseEntity<>(updatedTimesheet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable int id) {
        Optional<Timesheet> existingTimesheet = timesheetRepository.findById(id);
        if (existingTimesheet.isPresent()) {
            timesheetRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee-hours")
    public ResponseEntity<List<Map<String, Object>>> getEmployeeHoursByWeekEnding(
        @RequestParam("weekEnding") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate weekEnding) {
        List<Map<String, Object>> employeeHours = timesheetRepository.getEmployeeHoursByWeekEnding(weekEnding);
        if (employeeHours.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeHours, HttpStatus.OK);
    }
}