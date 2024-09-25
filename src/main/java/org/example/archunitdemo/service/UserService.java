package org.example.archunitdemo.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.archunitdemo.persistence.model.User;
import org.example.archunitdemo.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@NoArgsConstructor
//@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public void createUser(String name) {
        userRepository.save(new User(String.valueOf(Math.random()), name));
    }
}


