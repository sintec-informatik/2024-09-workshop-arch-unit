package org.example.archunitdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.example.archunitdemo.persistence.model.Order;
import org.example.archunitdemo.persistence.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> getOrderById(UUID orderId) {
        return orderRepository.findById(orderId);
    }

    public void placeOrder(Order order) {
        orderRepository.save(order);
    }
}

