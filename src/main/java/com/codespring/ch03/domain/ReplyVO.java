package com.codespring.ch03.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyVO {

    private Integer rno;
    private Integer bno;

    private String reply;
    private String replyer;
    private Date replyDate;
    private Date updateDate;
}
