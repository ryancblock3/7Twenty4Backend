package com.twenty4.WebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twenty4.WebApp.entity.Employee;
import com.twenty4.WebApp.entity.Invoice;
import com.twenty4.WebApp.entity.Job;
import com.twenty4.WebApp.repository.JobRepository;
import com.twenty4.WebApp.service.InvoiceService;
import com.twenty4.WebApp.entity.InvoiceEmployee;
import com.twenty4.WebApp.service.InvoiceEmployeeService;
import com.twenty4.WebApp.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final JobRepository jobRepository;
    private final InvoiceEmployeeService invoiceEmployeeService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, JobRepository jobRepository,
            InvoiceEmployeeService invoiceEmployeeService, EmployeeRepository employeeRepository) {
        this.invoiceService = invoiceService;
        this.jobRepository = jobRepository;
        this.invoiceEmployeeService = invoiceEmployeeService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Optional<Job> optionalJob = jobRepository.findById(invoice.getJob().getJobId());
        if (optionalJob.isPresent()) {
            invoice.setJob(optionalJob.get());

            Invoice savedInvoice = invoiceService.createInvoice(invoice);

            createAndPersistInvoiceEmployees(savedInvoice);

            return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private List<InvoiceEmployee> createAndPersistInvoiceEmployees(Invoice invoice) {
        List<InvoiceEmployee> invoiceEmployees = invoice.getInvoiceEmployees();
        if (invoiceEmployees != null) {
            for (InvoiceEmployee invoiceEmployee : invoiceEmployees) {
                Employee employee = null;
                int employeeId = 0;
                if (invoiceEmployee.getEmployee() != null
                        && (employeeId = invoiceEmployee.getEmployee().getEmployeeId()) != 0) {
                    employee = employeeRepository.findById(employeeId)
                            .orElse(null);
                }
                invoiceEmployee.setEmployee(employee);
                invoiceEmployee.setInvoice(invoice);
                invoiceEmployeeService.saveInvoiceEmployee(invoiceEmployee);
            }
        }
        return invoiceEmployees;
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @DeleteMapping("/{invoiceNumber}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable String invoiceNumber) {
        Optional<Invoice> optionalInvoice = invoiceService.getInvoiceByNumber(invoiceNumber);
        if (optionalInvoice.isPresent()) {
            invoiceService.deleteInvoice(invoiceNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<Invoice> getInvoiceByNumber(@PathVariable String invoiceNumber) {
        Optional<Invoice> optionalInvoice = invoiceService.getInvoiceByNumber(invoiceNumber);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkInvoiceExists(
            @RequestParam("jobId") Long jobId,
            @RequestParam("weekEnding") LocalDate weekEnding) {
        boolean exists = invoiceService.invoiceExists(jobId, weekEnding);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/job/{jobId}/weekEnding/{weekEnding}")
    public ResponseEntity<Invoice> getInvoiceByJobAndWeekEnding(
            @PathVariable Long jobId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate weekEnding) {
        System.out.println("Job ID: " + jobId);
        System.out.println("Week Ending: " + weekEnding);

        Optional<Invoice> optionalInvoice = invoiceService.getInvoiceByJobAndWeekEnding(jobId, weekEnding);
        return optionalInvoice.map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long invoiceId, @RequestBody Invoice updatedInvoice) {
        Optional<Invoice> optionalInvoice = invoiceService.getInvoiceById(invoiceId);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
            invoice.setWeekEnding(updatedInvoice.getWeekEnding());
            invoice.setTotalRegularHours(updatedInvoice.getTotalRegularHours());
            invoice.setTotalOvertimeHours(updatedInvoice.getTotalOvertimeHours());
            invoice.setTotalAmount(updatedInvoice.getTotalAmount());

            Invoice savedInvoice = invoiceService.updateInvoice(invoice);
            return new ResponseEntity<>(savedInvoice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/next-number")
    public ResponseEntity<Long> getNextInvoiceNumber() {
        Long nextInvoiceNumber = invoiceService.getNextInvoiceNumber();
        return ResponseEntity.ok(nextInvoiceNumber);
    }
}
