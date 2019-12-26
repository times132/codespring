package com.codespring.ch02.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TodoDTO {
    private String title;
    private Date dueDate;

    //@DateTimeFormat(pattern = "yyyy-mm-dd")
    //private Date dueDate;
}
