package com.example.ISG.controller.form;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter

public class DepartmentForm {
    private int id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String errorMessage;
}
