package org.example.archunitdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.archunitdemo.persistence.model.Product;
import org.example.archunitdemo.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> getProductById(UUID productId) {
        return productRepository.findById(productId);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
