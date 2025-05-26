package com.example.ISG.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User {
@id
private Integer id;

@Column
    private String account;
    private String password;
    private String name;
    private Integer branchId;
    private String account;
    private String account;
    private String account;

}
