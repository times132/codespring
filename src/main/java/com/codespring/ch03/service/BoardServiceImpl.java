package com.codespring.ch03.service;

import com.codespring.ch03.domain.BoardAttachVO;
import com.codespring.ch03.domain.BoardVO;
import com.codespring.ch03.domain.Criteria;
import com.codespring.ch03.mapper.BoardAttachMapper;
import com.codespring.ch03.mapper.BoardMapper;
import com.codespring.ch03.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

    @Setter(onMethod_ = {@Autowired})
    private BoardMapper mapper;

    @Setter(onMethod_ = {@Autowired})
    private BoardAttachMapper attachMapper;

    @Setter(onMethod_ = {@Autowired})
    private ReplyMapper replyMapper;

    @Transactional
    @Override
    public void register(BoardVO board) {
        log.info("register " + board);

        mapper.insertSelectKey(board);

        if (board.getAttachList() == null || board.getAttachList().size() <= 0){
            return;
        }

        board.getAttachList().forEach(attach -> {
            attach.setBno(board.getBno());
            attachMapper.insert(attach);
        });
    }

    @Override
    public BoardVO get(Integer bno) {
        log.info("get" + bno);

        return mapper.read(bno);
    }

    @Transactional
    @Override
    public boolean modify(BoardVO boardVO) {
        log.info("modify" + boardVO);

        attachMapper.deleteAll(boardVO.getBno());

        boolean modifyResult = mapper.update(boardVO) == 1;

        if (modifyResult && boardVO.getAttachList() != null && boardVO.getAttachList().size() > 0){
            boardVO.getAttachList().forEach(attach -> {
                attach.setBno(boardVO.getBno());
                attachMapper.insert(attach);
            });
        }

        return modifyResult;
    }

    @Transactional
    @Override
    public boolean remove(Integer bno) {
        log.info("remove" + bno);

        attachMapper.deleteAll(bno);
        replyMapper.deleteAll(bno);

        return mapper.delete(bno) == 1;
    }

//    @Override
//    public List<BoardVO> getList() {
//        log.info("getList");
//
//        return mapper.getList();
//    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        log.info("get List with criteria: " + cri);

        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("getTotalCount");

        return mapper.getTotalCount(cri);
    }

    @Override
    public List<BoardAttachVO> getAttachList(Integer bno) {
        log.info("get Attach List by bno " + bno);

        return attachMapper.findByBno(bno);
    }
}
