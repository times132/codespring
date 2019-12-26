package com.codespring.ch03.service;

import com.codespring.ch03.domain.Criteria;
import com.codespring.ch03.domain.ReplyPageDTO;
import com.codespring.ch03.domain.ReplyVO;
import com.codespring.ch03.mapper.BoardMapper;
import com.codespring.ch03.mapper.ReplyMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{

    @Setter(onMethod_ = {@Autowired})
    private ReplyMapper mapper;

    @Setter(onMethod_ = {@Autowired})
    private BoardMapper boardMapper;

    @Transactional
    @Override
    public int register(ReplyVO vo) {
        log.info("reply register: " + vo);

        boardMapper.updateReplyCnt(vo.getBno(), 1);

        return mapper.insert(vo);
    }

    @Override
    public ReplyVO read(int rno) {
        log.info("reply read: " + rno);

        return mapper.read(rno);
    }

    @Override
    public int modify(ReplyVO vo) {
        log.info("reply modify: " + vo);

        return mapper.update(vo);
    }

    @Transactional
    @Override
    public int remove(int rno) {
        log.info("reply remove: " + rno);

        ReplyVO vo = mapper.read(rno);

        boardMapper.updateReplyCnt(vo.getBno(), -1);

        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, int bno) {
        log.info("reply getList: " + bno);

        return mapper.getListWithPaging(cri, bno);
    }

    @Override
    public ReplyPageDTO getListPage(Criteria cri, int bno) {
        log.info("reply getListPage");

        return new ReplyPageDTO(mapper.getCountByBno(bno), mapper.getListWithPaging(cri, bno));
    }
}
