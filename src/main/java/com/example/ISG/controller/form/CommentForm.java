package com.example.ISG.controller.form;

import lombok.*;
import java.util.Date;

@Getter
@Setter

public class CommentForm {
    private int id;
    private String text;
    private String userId;
    private String messageId;
    private Date createdDate;
    private Date updatedDate;
    private String errorMessage;
}
