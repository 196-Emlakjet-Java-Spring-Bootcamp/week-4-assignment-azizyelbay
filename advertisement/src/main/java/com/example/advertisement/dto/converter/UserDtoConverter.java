package com.example.advertisement.dto.converter;

import com.example.advertisement.dto.UserDto;
import com.example.advertisement.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convert(User from) {
        return new UserDto(from.getName(),
                from.getSurname(),
                from.getEmail()
                );
    }
}
