package com.example.ISG.controller.form;

import lombok.*;
import java.util.Date;

@Getter
@Setter

public class BranchForm {
    private int id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private String errorMessage;
}
