package com.example.ISG.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.example.ISG.constfolder.ErrorMessage.*;

@Getter
@Setter

public class MessageForm {
    private int id;
    @NotBlank(message = E0006)
    @Size(max = 30, message = E0009)
    private String title;
    @NotBlank(message = E0007)
    @Size(max = 1000, message = E0010)
    private String text;
    @NotBlank(message = E0008)
    @Size(max = 10, message = E0011)
    private String category;
    private int userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String errorMessage;
}
