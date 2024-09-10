package com.twenty4.WebApp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_employee_id_seq")
    @SequenceGenerator(name = "employees_employee_id_seq", sequenceName = "employees_employee_id_seq", allocationSize = 1)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "email")
    private String email;

    @Column(name = "hourly_wage")
    private BigDecimal hourlyWage;

    @Column(name = "fica", insertable = false, updatable = false)
    private BigDecimal fica;

    @Column(name = "last_raise_date")
    private Date lastRaiseDate;

    @Column(name = "insurance")
    private BigDecimal insurance;

    @Column(name = "vacation_holiday")
    private BigDecimal vacationHoliday;

    @Column(name = "car_fuel")
    private BigDecimal carFuel;

    @Column(name = "markup")
    private BigDecimal markup;

    @Column(name = "rate", insertable = false, updatable = false)
    private BigDecimal rate;

    @Column(name = "burdened_rate", insertable = false, updatable = false)
    private BigDecimal burdenedRate;

    @Column(name = "burdened_ot_rate", insertable = false, updatable = false)
    private BigDecimal burdenedOtRate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "last_vacation_date")
    private Date lastVacationDate;

    @Column(name = "vacation_days_used")
    private Integer vacationDaysUsed;

    public Employee() {}

    public Employee(String name) {
        this.name = name;

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(BigDecimal hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public BigDecimal getFica() {
        return fica;
    }

    public void setFica(BigDecimal fica) {
        this.fica = fica;
    }

    public Date getLastRaiseDate() {
        return lastRaiseDate;
    }

    public void setLastRaiseDate(Date lastRaiseDate) {
        this.lastRaiseDate = lastRaiseDate;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getVacationHoliday() {
        return vacationHoliday;
    }

    public void setVacationHoliday(BigDecimal vacationHoliday) {
        this.vacationHoliday = vacationHoliday;
    }

    public BigDecimal getCarFuel() {
        return carFuel;
    }

    public void setCarFuel(BigDecimal carFuel) {
        this.carFuel = carFuel;
    }

    public BigDecimal getMarkup() {
        return markup;
    }

    public void setMarkup(BigDecimal markup) {
        this.markup = markup;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getBurdenedRate() {
        return burdenedRate;
    }

    public void setBurdenedRate(BigDecimal burdenedRate) {
        this.burdenedRate = burdenedRate;
    }

    public BigDecimal getBurdenedOtRate() {
        return burdenedOtRate;
    }

    public void setBurdenedOtRate(BigDecimal burdenedOtRate) {
        this.burdenedOtRate = burdenedOtRate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getLastVacationDate() {
        return lastVacationDate;
    }

    public void setLastVacationDate(Date lastVacationDate) {
        this.lastVacationDate = lastVacationDate;
    }

    public Integer getVacationDaysUsed() {
        return vacationDaysUsed;
    }

    public void setVacationDaysUsed(Integer vacationDaysUsed) {
        this.vacationDaysUsed = vacationDaysUsed;
    }
}
