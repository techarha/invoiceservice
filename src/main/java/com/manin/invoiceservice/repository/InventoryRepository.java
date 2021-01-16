package com.manin.invoiceservice.repository;

import com.manin.invoiceservice.model.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

}
