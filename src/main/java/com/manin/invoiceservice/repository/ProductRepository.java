package com.manin.invoiceservice.repository;

import com.manin.invoiceservice.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
