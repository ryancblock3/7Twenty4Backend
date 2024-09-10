package com.twenty4.WebApp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "week_ending")
    private LocalDate weekEnding;

    @Column(name = "total_regular_hours")
    private double totalRegularHours;

    @Column(name = "total_overtime_hours")
    private double totalOvertimeHours;

    @Column(name = "total_amount")
    private double totalAmount;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceEmployee> invoiceEmployees = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public double getTotalRegularHours() {
        return totalRegularHours;
    }

    public void setTotalRegularHours(double totalRegularHours) {
        this.totalRegularHours = totalRegularHours;
    }

    public double getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    public void setTotalOvertimeHours(double totalOvertimeHours) {
        this.totalOvertimeHours = totalOvertimeHours;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<InvoiceEmployee> getInvoiceEmployees() {
        return invoiceEmployees;
    }

    public void setInvoiceEmployees(List<InvoiceEmployee> invoiceEmployees) {
        this.invoiceEmployees = invoiceEmployees;
    }
}
