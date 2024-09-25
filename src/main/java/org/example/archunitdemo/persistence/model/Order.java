package org.example.archunitdemo.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private UUID orderId;

    private LocalDateTime orderDate;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Product> products;



}

