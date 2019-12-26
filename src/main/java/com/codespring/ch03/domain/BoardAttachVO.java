package com.codespring.ch03.domain;

import lombok.Data;

@Data
public class BoardAttachVO {

    private String uuid;
    private String uploadPath;
    private String fileName;
    private Boolean fileType;

    private Integer bno;
}
