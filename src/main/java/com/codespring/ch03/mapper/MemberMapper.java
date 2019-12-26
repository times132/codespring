package com.codespring.ch03.mapper;

import com.codespring.ch03.domain.MemberVO;

public interface MemberMapper {

    public MemberVO read(String userid);
}
