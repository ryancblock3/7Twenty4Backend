package com.twenty4.WebApp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "timesheets")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheets_timesheet_id_seq")
    @SequenceGenerator(name = "timesheets_timesheet_id_seq", sequenceName = "timesheets_timesheet_id_seq", allocationSize = 1)
    @Column(name = "timesheet_id")
    private int timesheetId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    private Job job;

    @Column(name = "week_ending")
    private LocalDate weekEnding;

    @Column(name = "monday_hours")
    private BigDecimal mondayHours;

    @Column(name = "tuesday_hours")
    private BigDecimal tuesdayHours;

    @Column(name = "wednesday_hours")
    private BigDecimal wednesdayHours;

    @Column(name = "thursday_hours")
    private BigDecimal thursdayHours;

    @Column(name = "friday_hours")
    private BigDecimal fridayHours;

    @Column(name = "saturday_hours")
    private BigDecimal saturdayHours;

    @Column(name = "sunday_hours")
    private BigDecimal sundayHours;

    @Column(name = "total_hours", insertable = false, updatable = false)
    private BigDecimal totalHours;

    public int getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(int timesheetId) {
        this.timesheetId = timesheetId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDate getWeekEnding() {
        return weekEnding;
    }

    public void setWeekEnding(LocalDate weekEnding) {
        this.weekEnding = weekEnding;
    }

    public BigDecimal getMondayHours() {
        return mondayHours;
    }

    public void setMondayHours(BigDecimal mondayHours) {
        this.mondayHours = mondayHours;
    }

    public BigDecimal getTuesdayHours() {
        return tuesdayHours;
    }

    public void setTuesdayHours(BigDecimal tuesdayHours) {
        this.tuesdayHours = tuesdayHours;
    }

    public BigDecimal getWednesdayHours() {
        return wednesdayHours;
    }

    public void setWednesdayHours(BigDecimal wednesdayHours) {
        this.wednesdayHours = wednesdayHours;
    }

    public BigDecimal getThursdayHours() {
        return thursdayHours;
    }

    public void setThursdayHours(BigDecimal thursdayHours) {
        this.thursdayHours = thursdayHours;
    }

    public BigDecimal getFridayHours() {
        return fridayHours;
    }

    public void setFridayHours(BigDecimal fridayHours) {
        this.fridayHours = fridayHours;
    }

    public BigDecimal getSaturdayHours() {
        return saturdayHours;
    }

    public void setSaturdayHours(BigDecimal saturdayHours) {
        this.saturdayHours = saturdayHours;
    }

    public BigDecimal getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(BigDecimal sundayHours) {
        this.sundayHours = sundayHours;
    }

    public BigDecimal getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(BigDecimal totalHours) {
        this.totalHours = totalHours;
    }
}
