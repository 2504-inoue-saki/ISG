package com.example.ISG.repository;

import com.example.ISG.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /*
     * ログイン処理（鈴木）
     */
    public List<User>  findByAccountAndPassword(String account, String password);
}
