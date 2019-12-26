package com.codespring.ch02.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
    @Select("SELECT CURDATE()")
    public String getTime();

    public String getTime2();
}
