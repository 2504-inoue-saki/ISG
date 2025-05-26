package com.example.ISG.controller.form;

import lombok.*;
import java.util.Date;

@Getter
@Setter

public class MessageForm {
    private int id;
    private String title;
    private String text;
    private String category;
    private int userId;
    private Date createdDate;
    private Date updatedDate;
    private String errorMessage;
}
