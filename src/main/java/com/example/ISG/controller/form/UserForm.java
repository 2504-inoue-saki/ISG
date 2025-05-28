package com.example.ISG.controller.form;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

import static com.example.ISG.constfolder.ErrorMessage.*;

@Getter
@Setter
public class UserForm {
    private int id;

    @NotEmpty(message = E0001)
//    @Size(min = 22, max = 6, message = E0014)
    private String account;

    @NotEmpty(message = E0002)
    private String password;

    @NotEmpty(message = E0019)
    @Size(max = 10, message = E0020)
    private String name;
    @Min(1)
    private int branchId;
    @Min(1)
    private int departmentId;

    private int isStopped;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String errorMessage;
    //下記鈴木追加
    @NotEmpty(message = E0001)
    private String checkPassword;
}
