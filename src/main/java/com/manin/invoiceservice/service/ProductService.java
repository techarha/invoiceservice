package com.manin.invoiceservice.service;

import com.manin.invoiceservice.model.Product;
import com.manin.invoiceservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }
}
