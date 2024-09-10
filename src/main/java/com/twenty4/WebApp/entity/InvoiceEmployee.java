package com.twenty4.WebApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_employees")
public class InvoiceEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "regular_hours")
    private double regularHours;

    @Column(name = "overtime_hours")
    private double overtimeHours;

    @Column(name = "regular_cost")
    private double regularCost;

    @Column(name = "overtime_cost")
    private double overtimeCost;

    @Column(name = "total_cost")
    private double totalCost;

    @Column(name = "burdened_rate")
    private double burdenedRate;

    @Column(name = "burdened_ot_rate")
    private double burdenedOtRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
    private Invoice invoice;

    @Column(name = "employee_name")
    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(double regularHours) {
        this.regularHours = regularHours;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getRegularCost() {
        return regularCost;
    }

    public void setRegularCost(double regularCost) {
        this.regularCost = regularCost;
    }

    public double getOvertimeCost() {
        return overtimeCost;
    }

    public void setOvertimeCost(double overtimeCost) {
        this.overtimeCost = overtimeCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getBurdenedRate() {
        return burdenedRate;
    }

    public void setBurdenedRate(double burdenedRate) {
        this.burdenedRate = burdenedRate;
    }

    public double getBurdenedOtRate() {
        return burdenedOtRate;
    }

    public void setBurdenedOtRate(double burdenedOtRate) {
        this.burdenedOtRate = burdenedOtRate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
