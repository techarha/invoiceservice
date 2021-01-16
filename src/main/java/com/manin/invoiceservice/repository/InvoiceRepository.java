package com.manin.invoiceservice.repository;

import com.manin.invoiceservice.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
}
