package com.codespring.ch03.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {

    private Integer replyCnt;
    private List<ReplyVO> list;
}
