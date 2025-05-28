package com.example.ISG.controller.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;

import static com.example.ISG.constfolder.ErrorMessage.*;

@Getter
@Setter
public class UserForm {
    private int id;
    @NotEmpty(message = E0001)
    @Size(min = 22, max = 6, message = E0014)
    private String account;
    @NotEmpty(message = E0002)
    private String password;
    @NotEmpty(message = E0019)
    @Size(max = 10, message = E0020)
    private String name;
    @NotNull(message = E0021)
    private int branchId;
    @NotNull(message = E0022)
    private int departmentId;
    private int isStopped;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String errorMessage;
    //下記鈴木追加
    @NotEmpty(message = E0001)
    private String checkPassword;
//    private String stringBranchId;
//    private String stringDepartmentId;
}
