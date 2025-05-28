package com.example.ISG.controller.form;

import com.example.ISG.constfolder.ErrorMessage;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

@Getter
@Setter

public class CommentForm {
    private int id;
    //空白だとcom/example/ISG/constfolder/ErrorMessage.javaに登録されているエラー文が表示される
    @NotBlank(message = ErrorMessage.E0007)
    @Size(max=200 , message = ErrorMessage.E0010)
    private String text;
    private int userId;
    private int messageId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String errorMessage;
}
