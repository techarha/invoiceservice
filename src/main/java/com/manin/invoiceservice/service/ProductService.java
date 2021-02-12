package com.manin.invoiceservice.service;

import com.manin.invoiceservice.exceptions.DummyException;
import com.manin.invoiceservice.model.Product;
import com.manin.invoiceservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            // throws a runtime exception, for now we throw a dummy exception just as service would
            throw new DummyException("INV-1", "Could not find invoiceID");
        }
        return productRepository.findById(id);
    }

    public Map<Integer, Product> getAllProductByIds(List<Integer> productIds) {
        Map<Integer, Product> productMap = new HashMap<>();
        productRepository.findAllById(productIds).forEach(p -> productMap.put(p.getId(), p));
        return productMap;
    }
}
