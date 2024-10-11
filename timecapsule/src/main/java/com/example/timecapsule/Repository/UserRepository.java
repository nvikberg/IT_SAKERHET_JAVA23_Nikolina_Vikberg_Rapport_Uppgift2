package com.example.timecapsule.Repository;

import com.example.timecapsule.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByName(String name);
    User findByMessage(String message);
}


