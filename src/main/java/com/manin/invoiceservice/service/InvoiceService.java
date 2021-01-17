package com.manin.invoiceservice.service;

import com.manin.invoiceservice.repository.CustomerRepository;
import com.manin.invoiceservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private CustomerRepository customerRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, CustomerRepository customerRepository) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
    }

    /**
     *
     * 1. a method that would create invoice and update inventory for the product.
     */

}
