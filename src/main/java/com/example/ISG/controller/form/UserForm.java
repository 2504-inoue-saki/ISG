package com.example.ISG.controller.form;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserForm {
    private int id;
    private String account;
    private String password;
    private String name;
    private int branchId;
    private int departmentId;
    private int isStopped;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String errorMessage;
}
