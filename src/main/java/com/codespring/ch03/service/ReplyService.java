package com.codespring.ch03.service;

import com.codespring.ch03.domain.Criteria;
import com.codespring.ch03.domain.ReplyPageDTO;
import com.codespring.ch03.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    public int register(ReplyVO vo);
    public ReplyVO read(int rno);
    public int modify(ReplyVO vo);
    public int remove(int rno);
    public List<ReplyVO> getList(Criteria cri, int bno);
    public ReplyPageDTO getListPage(Criteria cri, int bno);
}
