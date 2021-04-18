package com.manin.invoiceservice.service;

import com.manin.invoiceservice.model.Inventory;
import com.manin.invoiceservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Optional<Inventory> getInventoryById(String id) {
        return inventoryRepository.findById(id);
    }
}
