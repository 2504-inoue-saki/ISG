package com.example.ISG.controller.form;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter

public class MessageForm {
    private int id;
    private String title;
    private String text;
    private String category;
    private int userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String errorMessage;
}
