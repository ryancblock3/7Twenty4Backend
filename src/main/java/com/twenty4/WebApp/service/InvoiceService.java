package com.twenty4.WebApp.service;

import com.twenty4.WebApp.entity.Invoice;
import com.twenty4.WebApp.repository.InvoiceRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long invoiceId) {
        return invoiceRepository.findById(invoiceId);
    }

    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoice(String invoiceNumber) {
        invoiceRepository.deleteByInvoiceNumber(invoiceNumber);
    }

    public Optional<Invoice> getInvoiceByNumber(String invoiceNumber) {
        return invoiceRepository.findByInvoiceNumber(invoiceNumber);
    }

    public Optional<Invoice> getInvoiceByJobAndWeekEnding(Long jobId, LocalDate weekEnding) {
        return invoiceRepository.findByJobJobIdAndWeekEnding(jobId, weekEnding);
    }

    public boolean invoiceExists(Long jobId, LocalDate weekEnding) {
        return invoiceRepository.existsByJobJobIdAndWeekEnding(jobId, weekEnding);
    }

    public Long getNextInvoiceNumber() {
        Long maxInvoiceNumber = invoiceRepository.findMaxInvoiceNumber();
        return maxInvoiceNumber != null ? Math.max(maxInvoiceNumber + 1, 1630) : 1630;
    }
}
