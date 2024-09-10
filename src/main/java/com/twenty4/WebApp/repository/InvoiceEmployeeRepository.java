package com.twenty4.WebApp.repository;

import com.twenty4.WebApp.entity.InvoiceEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceEmployeeRepository extends JpaRepository<InvoiceEmployee, Long> {
}
