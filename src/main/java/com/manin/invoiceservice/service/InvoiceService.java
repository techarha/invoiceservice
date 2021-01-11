package com.manin.invoiceservice.service;

import com.manin.invoiceservice.repo.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

}
