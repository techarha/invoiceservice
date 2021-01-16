package com.manin.invoiceservice.repository;

import com.manin.invoiceservice.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
