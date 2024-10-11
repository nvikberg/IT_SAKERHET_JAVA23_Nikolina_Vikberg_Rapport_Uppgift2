package com.example.timecapsule.Repository;

import com.example.timecapsule.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByName(String name);
}