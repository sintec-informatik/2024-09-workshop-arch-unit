package org.example.archunitdemo.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    private UUID id;
    private String name;
    private LocalDateTime creationDate;

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.creationDate = LocalDateTime.now();
    }


}
