package com.example.advertisement.service;

import com.example.advertisement.model.SaleAdvertisement;
import com.example.advertisement.model.User;
import com.example.advertisement.repository.SaleAdvertisementRepository;
import com.example.advertisement.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaService {
    private final UserRepository userRepository;
    private final SaleAdvertisementRepository saleAdvertisementRepository;

    public KafkaService(UserRepository userRepository, SaleAdvertisementRepository saleAdvertisementRepository) {
        this.userRepository = userRepository;
        this.saleAdvertisementRepository = saleAdvertisementRepository;
    }

    // consume method
    @KafkaListener(
            topics = "user-topic",
            groupId = "group-id",
            containerFactory = "userKafkaListenerContainerFactory"
    )
    public void consumeUser(User user) {
        userRepository.save(user);
    }

    // consume method
    @KafkaListener(
            topics = "sale-topic",
            groupId = "group-id",
            containerFactory = "saleAdvertisementKafkaListenerContainerFactory"
    )
    public void consumeSaleAdvertisement(SaleAdvertisement saleAdvertisement) throws InterruptedException {
        Thread.sleep(2*1000);
        saleAdvertisement.setCreatedAt(LocalDateTime.now());
        saleAdvertisementRepository.save(saleAdvertisement);
    }
}
