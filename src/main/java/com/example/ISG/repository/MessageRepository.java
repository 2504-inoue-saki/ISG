package com.example.ISG.repository;

import com.example.ISG.repository.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    public List<Message> findByCreatedDateBetweenOrderByCreatedDateDesc(Date start, Date end);
}
