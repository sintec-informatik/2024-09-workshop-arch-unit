package org.example.archunitdemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.archunitdemo.persistence.model.Product;
import org.example.archunitdemo.persistence.repository.ProductRepository;
import org.example.archunitdemo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID productId) {
        Optional<Product> product = productService.getProductById(productId);
        return product.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{productName}")
    public ResponseEntity<Product> getProduct(@PathVariable String productName) {
        Optional<Product> product = productRepository.findByName(productName);
        return product.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok().build();
    }
}

