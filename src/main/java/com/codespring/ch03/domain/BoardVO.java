package com.codespring.ch03.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardVO {

    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Date updatedate;

    private Integer replycnt;

    private List<BoardAttachVO> attachList;
}
