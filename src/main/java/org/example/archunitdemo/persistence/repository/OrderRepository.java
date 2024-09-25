package org.example.archunitdemo.persistence.repository;

import org.example.archunitdemo.persistence.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepository {

    private final Map<UUID, Order> database = new HashMap<>();

    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    public void save(Order order) {
        database.put(order.getOrderId(), order);
    }

    public Map<UUID, Order> getOrderStorage() {
        return database;
    }
}
