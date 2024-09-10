package com.twenty4.WebApp.service;

import com.twenty4.WebApp.entity.InvoiceEmployee;
import com.twenty4.WebApp.repository.InvoiceEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceEmployeeService {
    private final InvoiceEmployeeRepository invoiceEmployeeRepository;

    @Autowired
    public InvoiceEmployeeService(InvoiceEmployeeRepository invoiceEmployeeRepository) {
        this.invoiceEmployeeRepository = invoiceEmployeeRepository;
    }

    public InvoiceEmployee saveInvoiceEmployee(InvoiceEmployee invoiceEmployee) {
        return invoiceEmployeeRepository.save(invoiceEmployee);
    }
}
