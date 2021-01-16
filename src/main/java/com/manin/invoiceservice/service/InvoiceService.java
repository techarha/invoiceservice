package com.manin.invoiceservice.service;

import com.manin.invoiceservice.model.Customer;
import com.manin.invoiceservice.repository.CustomerRepository;
import com.manin.invoiceservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private CustomerRepository customerRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, CustomerRepository customerRepository) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(c -> customers.add(c));
        return customers;
    }

}
