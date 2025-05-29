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
    @Size(min = 6, max = 20, message = E0014)
//    @Pattern(regexp = "正規表現あとでやる", message = E0014)
    private String account;

    @NotEmpty(message = E0002)
    @NotEmpty(message = E0016)
    @Size(min = 6, max = 20, message = E0017)
//    @Pattern(regexp = "正規表現あとでやる", message = E0017)
    private String password;

    @NotEmpty(message = E0019)
    @Size(max = 10, message = E0020)
    private String name;
    @Min(value = 1, message = E0021)
    private int branchId;
    @Min(value = 1, message = E0022)
    private int departmentId;

    private int isStopped;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String errorMessage;
    //下記鈴木追加
    @NotEmpty(message = E0001)
    private String checkPassword;
}
