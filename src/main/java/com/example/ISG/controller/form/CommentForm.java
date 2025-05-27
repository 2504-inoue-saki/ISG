package com.example.ISG.controller.form;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter

public class CommentForm {
    private int id;
    private String text;
    private int userId;
    private int messageId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String errorMessage;
}
