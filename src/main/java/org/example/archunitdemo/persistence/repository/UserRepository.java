package org.example.archunitdemo.persistence.repository;

import org.example.archunitdemo.persistence.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Map<String, User> database = new HashMap<>();

    public Optional<User> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }

    public void save(User user) {
        database.put(user.getId(), user);
    }
}
