package com.manin.invoiceservice.repo;

import com.manin.invoiceservice.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
}
