package com.example.advertisement.service;

import com.example.advertisement.model.User;
import com.example.advertisement.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final UserRepository userRepository;

    public KafkaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // consume
    @KafkaListener(topics = "user", groupId = "group-id")
    public void consume(User user) {

        userRepository.save(user);
    }
}
