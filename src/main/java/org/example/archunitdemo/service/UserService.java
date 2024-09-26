package org.example.archunitdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.archunitdemo.persistence.model.User;
import org.example.archunitdemo.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
//@NoArgsConstructor
@RequiredArgsConstructor
public class UserService {

//    @Autowired
    private final UserRepository userRepository;

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public void createUser(String name) {
        userRepository.save(new User(UUID.randomUUID(), name));
    }
}


