package com.codespring.ch03.service;

import com.codespring.ch03.domain.BoardAttachVO;
import com.codespring.ch03.domain.BoardVO;
import com.codespring.ch03.domain.Criteria;

import java.util.List;

public interface BoardService {
    public void register(BoardVO boardVO);
    public BoardVO get(Integer bno);
    public boolean modify(BoardVO boardVO);
    public boolean remove(Integer bno);
    //public List<BoardVO> getList();
    public List<BoardVO> getList(Criteria cri);
    public int getTotal(Criteria cri);
    public List<BoardAttachVO> getAttachList(Integer bno);
}
