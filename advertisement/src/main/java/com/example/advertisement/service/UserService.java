package com.example.advertisement.service;

import com.example.advertisement.dto.CreateUserRequest;
import com.example.advertisement.dto.UserDto;
import com.example.advertisement.dto.converter.UserDtoConverter;
import com.example.advertisement.model.User;
import com.example.advertisement.repository.SaleAdvertisementRepository;
import com.example.advertisement.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SaleAdvertisementRepository saleAdvertisementRepository;
    private final UserDtoConverter converter;
    private final KafkaTemplate<String, User> kafkaUserTemplate;
    private static final String[] name = {"Aziz","Mehmet","Ali","Veli","Ahmet","Ayşe","Dilara","Zeynep"};
    private static final String[] surname = {"Öztürk","Yıldırım","Yılmaz","Yelbay","Demir","Kaya","Aydın","Özdemir"};

    public UserService(UserRepository userRepository, SaleAdvertisementRepository saleAdvertisementRepository, UserDtoConverter converter, KafkaTemplate<String, User> kafkaUserTemplate) {
        this.userRepository = userRepository;
        this.saleAdvertisementRepository = saleAdvertisementRepository;
        this.converter = converter;
        this.kafkaUserTemplate = kafkaUserTemplate;
    }

    public UserDto createUser(CreateUserRequest createUserRequest){
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setSurname(createUserRequest.getSurname());
        user.setEmail(createUserRequest.getEmail());

        return converter.convert(userRepository.save(user));
    }

    public String createRandomUser() {
        Random random = new Random();

        int i = 0;
        while(i<50){

            User user = new User();
            user.setName(name[random.nextInt(name.length)]);
            user.setSurname(surname[random.nextInt(surname.length)]);
            user.setEmail(user.getName() + "." + user.getSurname() + "@gmail.com");

            kafkaUserTemplate.send("user-topic", user);
            i++;
        }

        return "Random users sent";
    }

    public User getUserById(Long userId) {

        return userRepository.findById(userId).orElse(null);
    }

    public User getRandomUser() {
        List<User> list = userRepository.findRandomUser();
        return list.get(0);
    }
}
