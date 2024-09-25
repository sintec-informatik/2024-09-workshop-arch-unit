package org.example.archunitdemo.persistence.repository;

import org.example.archunitdemo.persistence.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepository {

    private final Map<UUID, Product> productStorage = new HashMap<>();

    public Optional<Product> findById(UUID productId) {
        return Optional.ofNullable(productStorage.get(productId));
    }

    public void save(Product product) {
        productStorage.put(product.getProductId(), product);
    }

    public Optional<Product> findByName(String productName) {
        return productStorage.values().stream()
                .filter(product -> product.getProductName().equals(productName))
                .findFirst();
    }
}
