package com.codespring.ch03.mapper;

import com.codespring.ch03.domain.BoardAttachVO;

import java.util.List;

public interface BoardAttachMapper {

    public void insert(BoardAttachVO vo);
    public void delete(String uuid);
    public List<BoardAttachVO> findByBno(int bno);
    public void deleteAll(Integer bno);
}
