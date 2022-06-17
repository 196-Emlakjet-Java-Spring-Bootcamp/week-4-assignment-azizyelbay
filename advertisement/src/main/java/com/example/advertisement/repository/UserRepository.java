package com.example.advertisement.repository;

import com.example.advertisement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery=true, value="SELECT *  FROM users ORDER BY random() LIMIT 1")
    List<User> findRandomUser();
}
