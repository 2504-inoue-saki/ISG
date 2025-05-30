package com.example.ISG.repository;

import com.example.ISG.repository.entity.Comment;
import com.example.ISG.repository.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findAllByOrderByUpdatedDateDesc();
}
