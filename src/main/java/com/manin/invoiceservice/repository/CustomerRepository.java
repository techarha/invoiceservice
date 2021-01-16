package com.manin.invoiceservice.repository;

import com.manin.invoiceservice.model.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
