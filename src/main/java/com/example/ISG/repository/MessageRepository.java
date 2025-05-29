package com.example.ISG.repository;

import com.example.ISG.repository.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query
    public List<Message> findByCreatedDateBetweenAndCategoryOrderByCreatedDateDesc(LocalDateTime start, LocalDateTime end, String category);
    public List<Message> findByCreatedDateBetweenOrderByCreatedDateDesc(LocalDateTime start, LocalDateTime end);

}
