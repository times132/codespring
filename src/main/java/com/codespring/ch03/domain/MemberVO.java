package com.codespring.ch03.domain;

import com.codespring.ch03.domain.AuthVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MemberVO {

    private String userid;
    private String userpw;
    private String userName;
    private boolean enabled;

    private Date regdate;
    private Date updatedate;
    private List<AuthVO> authList;

}
