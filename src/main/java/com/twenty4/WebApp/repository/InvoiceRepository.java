package com.twenty4.WebApp.repository;

import com.twenty4.WebApp.entity.Invoice;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    void deleteByInvoiceNumber(String invoiceNumber);


    boolean existsByJobJobIdAndWeekEnding(Long jobId, LocalDate weekEnding);

    Optional<Invoice> findByJobJobIdAndWeekEnding(Long jobId, LocalDate weekEnding);

    @Query("SELECT MAX(i.invoiceNumber) FROM Invoice i")
    Long findMaxInvoiceNumber();

    
}
