package org.example.archunitdemo.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    private String id;
    private String name;
    private LocalDateTime creationDate;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.creationDate = LocalDateTime.now();
    }


}
