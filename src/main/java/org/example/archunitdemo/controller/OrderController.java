package org.example.archunitdemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.archunitdemo.persistence.model.Order;
import org.example.archunitdemo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
//    private final OrderRepository orderRepository;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable UUID orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> placeOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        return ResponseEntity.ok().build();
    }
}

