package org.example.archunitdemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.archunitdemo.persistence.model.User;
import org.example.archunitdemo.persistence.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping("/create")
    public void createUser(@RequestParam String newName) {
        User user = new User(UUID.randomUUID(), newName);
//        user.setCreationDate(LocalDateTime.now());
        repository.save(user);
    }
}

