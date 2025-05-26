package com.example.ISG.controller.form;

import lombok.*;
import java.util.Date;

@Getter
@Setter
public class UserForm {
    private int id;
    private String account;
    private String password;
    private String name;
    private int branchId;
    private int departmentId;
    private int isStoppedId;
    private Date createdDate;
    private Date updatedDate;
    private String errorMessage;
}
